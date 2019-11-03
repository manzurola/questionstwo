package com.prodigy.webapp.api;

import com.prodigy.domain.Answer;
import com.prodigy.service.impl.SubmitAnswerRequest;
import com.prodigy.webapp.api.calls.AddQuestionApiCall;
import com.prodigy.webapp.api.calls.SubmitAnswerApiCall;
import com.prodigy.testdata.TestQuestions;
import com.prodigy.webapp.api.env.EndToEndTest;
import com.prodigy.domain.Question;
import com.prodigy.service.AddQuestionRequest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuestionControllerTest extends EndToEndTest {

    private TestQuestions testQuestions = new TestQuestions();

    @Test
    public void addQuestion() {
        Question expected = testQuestions.random();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(new AddQuestionRequest(expected), template, baseUrl);
        Question actual = response.getBody();
        assertEqualsIgnoreId(expected, actual);
    }

    @Test
    public void getQuestion() {
        Question expected = testQuestions.random();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(new AddQuestionRequest(expected), template, baseUrl);
        Question actual = response.getBody();
    }

    @Test
    public void solveQuestion() {
        Question expected = testQuestions.random();
        Question question = new AddQuestionApiCall().run(new AddQuestionRequest(expected), template, baseUrl).getBody();
        ResponseEntity<Answer> answer = new SubmitAnswerApiCall().run(
                new SubmitAnswerRequest(question.getId(), "my answer"),
                template,
                baseUrl
        );

        System.out.println(answer.getBody());
        assertTrue(answer.getStatusCode().is2xxSuccessful());
    }

    private void assertEqualsIgnoreId(Question expected, Question actual) {
        assertEquals(expected.getBody(), actual.getBody());
        assertEquals(expected.getAnswerKey(), actual.getAnswerKey());
        assertEquals(expected.getInstructions(), actual.getInstructions());
    }
}