package com.prodigy.api;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.answers.SubmitAnswerRequest;
import com.prodigy.api.test.AddQuestionApiCall;
import com.prodigy.api.test.AddUserApiCall;
import com.prodigy.api.questions.utils.QuestionUtils;
import com.prodigy.api.test.SubmitAnswerApiCall;
import com.prodigy.api.env.EndToEndTest;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.users.User;
import com.prodigy.api.users.request.AddUserRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class AnswerControllerTest extends EndToEndTest {

    @Autowired
    private QuestionUtils questionUtils;

    @Test
    public void submitAnswer() {
        AddQuestionRequest addQuestionRequest = questionUtils.randomAddQuestionRequest();
        ResponseEntity<Question> addQuestionResponse = new AddQuestionApiCall().run(addQuestionRequest, template, baseUrl);
        Question question = addQuestionResponse.getBody();

        AddUserRequest addUserRequest = new AddUserRequest("guym@guy.com");
        ResponseEntity<User> addUserResponse = new AddUserApiCall().run(addUserRequest, template, baseUrl);
        User user = addUserResponse.getBody();

        SubmitAnswerRequest submitAnswerRequest = new SubmitAnswerRequest(user.getId(), question.getId(), "my answer");
        ResponseEntity<Answer> submitAnswerResponse = new SubmitAnswerApiCall().run(submitAnswerRequest, template, baseUrl);
        Answer actual = submitAnswerResponse.getBody();
        Answer expected = Answer.builder()
                .id(actual.getId())
                .userId(user.getId())
                .questionId(question.getId())
                .input(submitAnswerRequest.getAnswer())
                .build();

        assertEquals(expected, actual);
    }
}
