package com.prodigy.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodigy.domain.repository.AnswerRepository;
import com.prodigy.infrastructure.repository.InMemoryAnswerRepository;
import com.prodigy.readers.QuestionCSVParser;
import com.prodigy.readers.QuestionCSVParserV1;
import com.prodigy.infrastructure.serialization.ObjectMapperFactory;
import com.prodigy.domain.diff.ListDiffCheck;
import com.prodigy.domain.diff.impl.DMPListDiffCheck;
import com.prodigy.domain.Reviewer;
import com.prodigy.domain.SmartReviewer;
import com.prodigy.application.command.*;
import com.prodigy.application.common.CommandProcessorImpl;
import com.prodigy.domain.Question;
import com.prodigy.infrastructure.repository.InMemoryQuestionRepository;
import com.prodigy.domain.repository.QuestionRepository;
import com.prodigy.readers.QuestionCSVReader;
import com.prodigy.domain.nlp.SentenceFactory;
import com.prodigy.domain.nlp.impl.CoreNLPSentenceFactory;
import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.diff.impl.SentenceDiffCheckerImpl;
import com.prodigy.application.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.prodigy")
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
        QuestionCSVReader reader = new QuestionCSVReader(questionCSVParser());
        URL resource = this.getClass().getClassLoader().getResource("questions-en.csv");
        List<Question> data = new ArrayList<>(reader.readAll(new InputStreamReader(resource.openStream())));
        return new InMemoryQuestionRepository(data);
    }

    @Bean
    public QuestionCSVParser questionCSVParser() {
        return new QuestionCSVParserV1();
    }

    @Bean
    public CommandProcessor commandProcessor() throws Exception {
        return new CommandProcessorImpl(commandHandlerProvider());
    }

    @Bean
    public CommandHandlerProvider commandHandlerProvider() throws Exception {
        return new DefaultCommandHandlerProvider.Builder()
                .register(AddQuestionCommand.class, addQuestionCommandHandler())
                .register(AddAnswerCommand.class, addAnswerCommandHandler())
                .build();
    }

    @Bean
    public CommandHandler<AddQuestionCommand> addQuestionCommandHandler() throws Exception {
        return new AddQuestionCommandHandler(questionRepository());
    }

    @Bean
    public CommandHandler<AddAnswerCommand> addAnswerCommandHandler() throws Exception {
        return new AddAnswerCommandHandler(null, answerRepository());//TODO fix
    }

    @Bean
    public QueryHandlerProvider queryHandlerProvider() throws Exception {
        return new DefaultQueryHandlerProvider.Builder()
                .register(GetQuestionsQuery.class, getQuestionsQueryHandler())
                .register(GetQuestionByIdQuery.class, getQuestionByIdQueryHandler())
                .build();
    }

    @Bean
    public QueryHandler<GetQuestionsQuery, List<Question>> getQuestionsQueryHandler() throws Exception {
        return new GetQuestionsQueryHandler(questionRepository());
    }

    @Bean
    public QueryHandler<GetQuestionByIdQuery, Question> getQuestionByIdQueryHandler() throws Exception {
        return new GetQuestionByIdQueryHandler(questionRepository());
    }

    @Bean
    public AnswerRepository answerRepository() {
        return new InMemoryAnswerRepository();
    }


//    @Bean
//    public ContractionResolver contractionResolver() throws FileNotFoundException {
//        String file = this.getClass().getClassLoader().getResourcadde("en-data-exercises.csv").getFile();
//        return new ContractionResolverImpl(new FileReader(file));
//    }

    @Bean
    public Reviewer reviewer() throws Exception {
        return new SmartReviewer(sentenceFactory(), sentenceDiffChecker());
    }

    @Bean
    public SentenceFactory sentenceFactory() {
        return new CoreNLPSentenceFactory();
    }


    @Bean
    public SentenceDiffChecker sentenceDiffChecker() {
        return new SentenceDiffCheckerImpl(listDiffCheck());
    }

    @Bean
    public ListDiffCheck listDiffCheck() {
        return new DMPListDiffCheck();
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
        return ObjectMapperFactory.defaultObjectMapper();
    }

}