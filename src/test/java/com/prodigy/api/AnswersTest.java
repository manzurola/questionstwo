package com.prodigy.api;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.env.EndToEndTest;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.users.User;
import com.prodigy.api.users.request.AddUserRequest;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AnswersTest extends EndToEndTest {

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
                baseUrl.toString() + "/questions",
                HttpMethod.POST,
                new HttpEntity<>(addQuestionRequest),
                new ParameterizedTypeReference<Question>() {
                });
        Question question = addQuestionResponse.getBody();

        AddUserRequest addUserRequest = new AddUserRequest("guym@guy.com");
        ResponseEntity<User> addUserResponse = template.exchange(
                baseUrl.toString() + "/users",
                HttpMethod.POST,
                new HttpEntity<>(addUserRequest),
                new ParameterizedTypeReference<User>() {
                });
        User user = addUserResponse.getBody();

        SubmitAnswerRequest submitAnswerRequest = new SubmitAnswerRequest(user.getId(), question.getId(), "my answer");
        ResponseEntity<Answer> submitAnswerResponse = template.exchange(
                baseUrl.toString() + "/answers",
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
