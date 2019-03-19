package com.prodigy.api;

import com.prodigy.api.env.ElasticsearchCollaborator;
import com.prodigy.api.users.User;
import com.prodigy.api.users.request.AddUserRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clustername}")
    private String elasticsearchClusterName;

    @Value("${elasticsearch.hostname}")
    private String elasticsearchHostName;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    private ElasticsearchCollaborator elasticsearchCollaborator;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/users");
        elasticsearchCollaborator = new ElasticsearchCollaborator(elasticsearchPort, elasticsearchClusterName).start();
    }

    @After
    public void tearDown() throws Exception {
        elasticsearchCollaborator.stop();
    }


    @Test
    public void addUser() throws Exception {
        AddUserRequest request = new AddUserRequest("guym@guy.com");

        ResponseEntity<User> response = template.exchange(
                base.toString(),
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<User>() {
                });

        User actual = response.getBody();

        User expected = User.builder()
                .setId(actual.getId())
                .setEmail(request.getEmail())
                .build();

        assertEquals(actual, expected);
    }
}