package com.prodigy.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodigy.common.jackson.ObjectMapperConfig;
import com.prodigy.diff.*;
import com.prodigy.review.Reviewer;
import com.prodigy.review.SmartReviewer;
import com.prodigy.common.service.CommandFactory;
import com.prodigy.common.service.ServiceExecutor;
import com.prodigy.common.service.ServiceExecutorImpl;
import com.prodigy.questions.domain.Question;
import com.prodigy.questions.database.InMemoryQuestionRepository;
import com.prodigy.questions.database.QuestionRepository;
import com.prodigy.questions.readers.AddQuestionRequestCSVReader;
import com.prodigy.questions.readers.AddQuestionRequestReader;
import com.prodigy.grammar.SentenceFactory;
import com.prodigy.grammar.corenlp.CoreSentenceWrapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return new SmartReviewer(sentenceFactory(), sentenceDiffChecker());
    }

    @Bean
    public SentenceFactory sentenceFactory() {
        return new CoreSentenceWrapperFactory();
    }

    @Bean
    public SentenceDiffCheck sentenceDiffChecker() {
        return new SentenceDiffCheckImpl(wordDiffCheck());
    }

    @Bean
    public WordDiffCheck wordDiffCheck() {
        return new WordDiffCheckImpl();
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
        return ObjectMapperConfig.getObjectMapper();
    }

}