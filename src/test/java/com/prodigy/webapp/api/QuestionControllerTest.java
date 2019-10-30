package com.prodigy.webapp.api;

import com.prodigy.domain.Answer;
import com.prodigy.service.impl.SubmitAnswerRequest;
import com.prodigy.webapp.api.calls.AddQuestionApiCall;
import com.prodigy.webapp.api.calls.SubmitAnswerApiCall;
import com.prodigy.testdata.TestQuestions;
import com.prodigy.webapp.api.env.EndToEndTest;
import com.prodigy.domain.Question;
import com.prodigy.domain.questions.service.AddQuestionRequest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionControllerTest extends EndToEndTest {

    private TestQuestions testQuestions = new TestQuestions();


    @Test
    public void addQuestion() {
        AddQuestionRequest request = testQuestions.randomRequest();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(request, template, baseUrl);
        Question actual = response.getBody();
        Question expected = request.toQuestion().withId(actual.getId()).build();
        assertEquals(expected, actual);
    }

    @Test
    public void getQuestion() {
        AddQuestionRequest request = testQuestions.randomRequest();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(request, template, baseUrl);
        Question actual = response.getBody();
    }

    @Test
    public void solveQuestion() {
        AddQuestionRequest request = testQuestions.randomRequest();
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