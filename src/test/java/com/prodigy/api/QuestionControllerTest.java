package com.prodigy.api;

import com.prodigy.domain.answers.Answer;
import com.prodigy.domain.answers.request.SubmitAnswerRequest;
import com.prodigy.api.calls.AddQuestionApiCall;
import com.prodigy.api.calls.SubmitAnswerApiCall;
import com.prodigy.utils.QuestionTestData;
import com.prodigy.api.env.EndToEndTest;
import com.prodigy.domain.questions.Question;
import com.prodigy.domain.questions.request.AddQuestionRequest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionControllerTest extends EndToEndTest {

    private QuestionTestData questionTestData = new QuestionTestData();


    @Test
    public void addQuestion() {
        AddQuestionRequest request = questionTestData.random();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(request, template, baseUrl);
        Question actual = response.getBody();
        Question expected = request.toQuestion().withId(actual.getId()).build();
        assertEquals(expected, actual);
    }

    @Test
    public void getQuestion() {
        AddQuestionRequest request = questionTestData.random();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(request, template, baseUrl);
        Question actual = response.getBody();
    }

    @Test
    public void solveQuestion() {
        AddQuestionRequest request = questionTestData.random();
        Question question = new AddQuestionApiCall().run(request, template, baseUrl).getBody();
        ResponseEntity<Answer> answer = new SubmitAnswerApiCall().run(
                new SubmitAnswerRequest(question.getId(), "my answer"),
                template,
                baseUrl
        );

        System.out.println(answer.getBody());
        assertTrue(answer.getStatusCode().is2xxSuccessful());
    }
}