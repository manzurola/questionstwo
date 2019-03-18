package com.prodigy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.prodigy.api.common.DataStore;
import com.prodigy.api.common.ElasticsearchDataStore;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.common.service.ServiceExecutorImpl;
import com.prodigy.api.questions.data.ElasticsearchQuestionRepository;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.service.command.AddQuestionCommand;
import com.prodigy.api.questions.service.QuestionService;
import com.prodigy.api.questions.service.QuestionServiceImpl;
import com.prodigy.api.questions.service.command.GetAllQuestionsCommand;
import com.prodigy.api.users.ElasticsearchUserRepository;
import com.prodigy.api.users.UserRepository;
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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

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
    public DataStore dataStore(TransportClient client, ObjectMapper mapper, ElasticsearchDataStore.Mappings mappings) {
        return new ElasticsearchDataStore(client, mapper, mappings);
    }

    @Bean
    public AddQuestionCommand addQuestionCommand(QuestionRepository questionRepository) {
        return new AddQuestionCommand(questionRepository);
    }

    @Bean
    public GetAllQuestionsCommand getAllQuestionsCommand(QuestionRepository questionRepository) {
        return new GetAllQuestionsCommand(questionRepository);
    }

    @Bean
    public QuestionRepository questionRepository() throws Exception {
        return new ElasticsearchQuestionRepository(transportClient(), objectMapper());
    }

    @Bean
    public QuestionService questionService() throws Exception {
        return new QuestionServiceImpl(questionRepository());
    }

    @Bean
    public ServiceExecutor serviceExecutor() {
        return new ServiceExecutorImpl(applicationContext::getBean);
    }

    @Bean
    public UserRepository userRepository() throws UnknownHostException {
        return new ElasticsearchUserRepository(transportClient(), objectMapper());
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());
        mapper.setVisibility(FIELD, ANY);

        return mapper;
    }

    @Bean
    public ElasticsearchDataStore.Mappings elasticsearchDataStoreMappings() {
        return new ElasticsearchDataStore.Mappings();
    }


}