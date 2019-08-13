package com.prodigy.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.prodigy.api.answers.review.Reviewer;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.jackson.IdDeserializer;
import com.prodigy.api.common.jackson.IdSerializer;
import com.prodigy.api.common.service.CommandFactory;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.common.service.ServiceExecutorImpl;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.InMemoryQuestionRepository;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.utils.AddQuestionRequestCSVReader;
import com.prodigy.api.questions.utils.AddQuestionRequestReader;
import com.prodigy.core.diff.DMPDiffCalculator;
import com.prodigy.core.diff.SentenceDiffChecker;
import com.prodigy.core.nlp.PTBSentenceTokenizer;
import com.prodigy.core.nlp.SentenceTokenizer;
import com.prodigy.core.nlp.WhitespaceSentenceTokenizer;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class Application {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext applicationContext;

    //    @Value("${jetty.port}")
    private Integer jettyPort;

    //    @Value("${jetty.host}")
    private String jettyHost;

    //    @Value("${elasticsearch.index}")
    private String elasticsearchIndex;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clustername}")
    private String elasticsearchClusterName;

    @Value("${elasticsearch.hostname}")
    private String elasticsearchHostName;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    public QuestionRepository questionRepository() throws Exception {
        AddQuestionRequestReader reader = new AddQuestionRequestCSVReader(new File(this.getClass().getClassLoader().getResource("questions-en.csv").getFile()));
        List<Question> data = reader.readAll()
                .stream()
                .map(request -> request.toQuestion().build())
                .collect(Collectors.toList());
        return new InMemoryQuestionRepository(data);
    }

    @Bean
    public ServiceExecutor serviceExecutor() {
        return new ServiceExecutorImpl(commandFactory());
    }

    @Bean
    public CommandFactory commandFactory() {
        return applicationContext::getBean;
    }

//    @Bean
//    public ContractionResolver contractionResolver() throws FileNotFoundException {
//        String file = this.getClass().getClassLoader().getResourcadde("en-data-exercises.csv").getFile();
//        return new ContractionResolverImpl(new FileReader(file));
//    }

    @Bean
    public Reviewer reviewer() throws Exception {
        return new Reviewer(whitespaceSentenceTokenizer(), sentenceDiffChecker());
    }

    @Bean
    SentenceTokenizer whitespaceSentenceTokenizer() {
        return new WhitespaceSentenceTokenizer();
    }

    @Bean
    SentenceTokenizer ptbSentenceTokenizer() {
        return new PTBSentenceTokenizer();
    }

    @Bean
    public SentenceDiffChecker sentenceDiffChecker() {
        return new SentenceDiffChecker(new DMPDiffCalculator(new DiffMatchPatch()));
    }

//    @Bean
//    public TransportClient transportClient() throws UnknownHostException {
//        Settings settings = Settings.builder()
//                .put("cluster.name", elasticsearchClusterName).build();
//        TransportClient client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticsearchHostName), elasticsearchPort));
//
//        return client;
//    }

    @Bean
    public ObjectMapper objectMapper() {

        SimpleModule module = new SimpleModule();
        module.addSerializer(Id.class, new IdSerializer());
        module.addDeserializer(Id.class, new IdDeserializer());

        ObjectMapper mapper = new ObjectMapper()
                .setVisibility(FIELD, ANY)
                .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .registerModule(module);

        return mapper;
    }

}