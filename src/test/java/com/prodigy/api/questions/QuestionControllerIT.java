package com.prodigy.api.questions;

import com.prodigy.api.Application;
import com.prodigy.api.questions.domain.AddQuestionRequest;
import com.prodigy.api.questions.domain.Question;
import com.prodigy.api.test.ElasticsearchCollaborator;
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
public class QuestionControllerIT {

    @LocalServerPort
    private int port;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clustername}")
    private String  elasticsearchClusterName;

    @Value("${elasticsearch.hostname}")
    private String  elasticsearchHostName;

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
    public void tearDown() throws  Exception {
        elasticsearchCollaborator.stop();
    }

    @Test
    public void getQuestionsReturnsEmptyList() throws Exception {
        ResponseEntity<List<Question>> response = template.exchange(
                base.toString() + "questions/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Question>>() {
                });
        List<Question> questions = response.getBody();
        assertEquals(questions, Arrays.asList());
    }

    @Test
    public void addQuestion() throws Exception {
        AddQuestionRequest request = new AddQuestionRequest(
                new Question.Builder()
                .setBody("this is the body")
                .setAnswerKey(Arrays.asList("answer1", "answer2"))
                .setInstructions("do it")
                .setSubject("life")
                .setSource("guyman")
                .setVersion("1")
        );
        ResponseEntity<Question> response = template.exchange(
                base.toString() + "questions/",
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<Question>() {
                });
        Question question = response.getBody();
        Question expected = new Question.Builder(question)
                .merge(request.getQuestion().build())
                .build();
        assertEquals(question, expected);
    }
}