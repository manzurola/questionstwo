package com.prodigy.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.prodigy.api.common.DataStore;
import com.prodigy.api.common.ElasticsearchDataStore;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.jackson.IdDeserializer;
import com.prodigy.api.common.jackson.IdSerializer;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.common.service.ServiceExecutorImpl;
import com.prodigy.api.exercises.ExerciseRepository;
import com.prodigy.api.exercises.InMemoryExerciseRepository;
import com.prodigy.api.exercises.utils.CSVExerciseReader;
import com.prodigy.api.exercises.utils.ExerciseReader;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.InMemoryQuestionRepository;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.questions.utils.AddQuestionRequestCSVReader;
import com.prodigy.api.questions.utils.AddQuestionRequestReader;
import com.prodigy.api.questions.utils.QuestionUtils;
import com.prodigy.api.users.data.ElasticsearchUserRepository;
import com.prodigy.api.users.data.UserRepository;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.StanfordSentenceParser;
import com.prodigy.nlp.diff.DMPTextDiffCalculator;
import com.prodigy.nlp.diff.SentenceDiffCheck;
import com.prodigy.nlp.diff.SentenceDiffCheckImpl;
import com.prodigy.nlp.diff.TextDiffCalculator;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import name.fraser.neil.plaintext.diff_match_patch;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
    public DataStore dataStore() throws UnknownHostException {
        return new ElasticsearchDataStore(transportClient(), objectMapper());
    }

    @Bean
    public QuestionRepository questionRepository() throws Exception {
        QuestionUtils utils = new QuestionUtils();
        List<Question> data = utils.getQuestions()
                .stream()
                .map(request -> utils.newQuestionFromRequest(request).build())
                .collect(Collectors.toList());
        return new InMemoryQuestionRepository(data);
    }

    @Bean
    public ExerciseRepository exerciseRepository(ExerciseReader reader) throws Exception {
        return new InMemoryExerciseRepository(reader.readAll());
    }

    @Bean
    public ExerciseReader exerciseReader() throws IOException {
        return new CSVExerciseReader(
                new File(this.getClass().getClassLoader().getResource("en-data-exercises.csv").getFile()),
                new File(this.getClass().getClassLoader().getResource("en-data-questions.csv").getFile())
        );
    }

    @Bean
    public TextDiffCalculator diffCalculator() {
        return new DMPTextDiffCalculator(new diff_match_patch());
    }

    @Bean
    public ServiceExecutor serviceExecutor() {
        return new ServiceExecutorImpl(applicationContext::getBean);
    }

    @Bean
    public UserRepository userRepository() throws UnknownHostException {
        return new ElasticsearchUserRepository(dataStore());
    }

    @Bean
    public SentenceParser sentenceParser() {
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);
        return new StanfordSentenceParser(tagger, parser);
    }

    @Bean
    public SentenceDiffCheck diffCheck() {
        return new SentenceDiffCheckImpl(diffCalculator());
    }

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", elasticsearchClusterName).build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticsearchHostName), elasticsearchPort));

        return client;
    }

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