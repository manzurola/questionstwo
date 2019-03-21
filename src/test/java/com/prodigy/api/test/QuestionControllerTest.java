package com.prodigy.api.test;

import com.prodigy.api.test.env.EndToEndTest;
import com.prodigy.api.test.questions.Question;
import com.prodigy.api.test.questions.request.AddQuestionRequest;
import com.prodigy.api.test.test.AddQuestionApiCall;
import com.prodigy.api.test.test.QuestionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionControllerTest extends EndToEndTest {

    @Autowired
    private QuestionUtils questionUtils;

    @Test
    public void getQuestionsReturnsEmptyList() {
        ResponseEntity<List<Question>> response = template.exchange(
                baseUrl.toString() + "/questions",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Question>>() {
                });
        List<Question> questions = response.getBody();
        assertEquals(Arrays.asList(), questions);
    }

    @Test
    public void addQuestion() {

        AddQuestionRequest request = questionUtils.randomAddQuestionRequest();

        ResponseEntity<Question> response = new AddQuestionApiCall().run(request, template, baseUrl);

        Question actual = response.getBody();

        Question expected = questionUtils.newQuestionFromRequest(request).id(actual.getId()).build();

        assertEquals(actual, expected);
    }
}