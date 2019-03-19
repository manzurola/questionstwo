package com.prodigy.api;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
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
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnswersTest {

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
    public void submitAnswer() {
        AddQuestionRequest addQuestionRequest = new AddQuestionRequest(
                "this is the body",
                Arrays.asList("answer1", "answer2"),
                "do it",
                "life",
                "guyman",
                "1"
        );
        ResponseEntity<Question> addQuestionResponse = template.exchange(
                base.toString() + "questions",
                HttpMethod.POST,
                new HttpEntity<>(addQuestionRequest),
                new ParameterizedTypeReference<Question>() {
                });
        Question question = addQuestionResponse.getBody();

        AddUserRequest addUserRequest = new AddUserRequest("guym@guy.com");
        ResponseEntity<User> addUserResponse = template.exchange(
                base.toString() + "users",
                HttpMethod.POST,
                new HttpEntity<>(addUserRequest),
                new ParameterizedTypeReference<User>() {
                });
        User user = addUserResponse.getBody();

        SubmitAnswerRequest submitAnswerRequest = new SubmitAnswerRequest(user.getId(), question.getId(), "my answer");
        ResponseEntity<Answer> submitAnswerResponse = template.exchange(
                base.toString() + "answers",
                HttpMethod.POST,
                new HttpEntity<>(submitAnswerRequest),
                new ParameterizedTypeReference<Answer>() {
                });
        Answer actual = submitAnswerResponse.getBody();
        Answer expected = Answer.builder()
                .setId(actual.getId())
                .setUserId(user.getId())
                .setQuestionId(question.getId())
                .setAnswer(submitAnswerRequest.getAnswer())
                .build();

        assertEquals(expected, actual);
    }
}
