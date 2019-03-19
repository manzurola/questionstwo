package com.prodigy.api;

import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.env.ElasticsearchCollaborator;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {

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
        this.base = new URL("http://localhost:" + port + "/");
        elasticsearchCollaborator = new ElasticsearchCollaborator(elasticsearchPort, elasticsearchClusterName).start();
    }

    @After
    public void tearDown() throws Exception {
        elasticsearchCollaborator.stop();
    }

    @Test
    public void getQuestionsReturnsEmptyList() throws Exception {
        ResponseEntity<List<Question>> response = template.exchange(
                base.toString() + "questions/",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Question>>() {
                });
        List<Question> questions = response.getBody();
        assertEquals(Arrays.asList(), questions);
    }

    @Test
    public void addQuestion() throws Exception {
        AddQuestionRequest request = new AddQuestionRequest(
                "this is the body",
                Arrays.asList("answer1", "answer2"),
                "do it",
                "life",
                "guyman",
                "1"
        );
        ResponseEntity<Question> response = template.exchange(
                base.toString() + "questions/",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<Question>() {
                });

        Question actual = response.getBody();

        Question expected = new Question.Builder()
                .id(actual.getId())
                .answerKey(request.getAnswerKey())
                .body(request.getBody())
                .instructions(request.getInstructions())
                .source(request.getSource())
                .subject(request.getSubject())
                .version(request.getVersion())
                .build();

        assertEquals(actual, expected);
    }
}