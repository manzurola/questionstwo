package com.prodigy.api.env;

import com.prodigy.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class EndToEndTest {

    @LocalServerPort
    private int port;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clustername}")
    private String elasticsearchClusterName;

    @Value("${elasticsearch.hostname}")
    private String elasticsearchHostName;

    private EmbeddedElasticsearch embeddedElasticsearch;

    protected URL baseUrl;


    @Autowired
    protected TestRestTemplate template;


    @Before
    public void setUp() throws Exception {
        this.baseUrl = new URL("http://localhost:" + port);
//        embeddedElasticsearch = new EmbeddedElasticsearch(elasticsearchPort, elasticsearchClusterName).start();
    }

    @After
    public void tearDown() throws Exception {
//        embeddedElasticsearch.stop();
    }

}
