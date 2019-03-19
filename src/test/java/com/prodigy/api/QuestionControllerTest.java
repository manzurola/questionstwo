package com.prodigy.api;

import com.prodigy.api.env.EndToEndTest;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionControllerTest extends EndToEndTest {

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
        AddQuestionRequest request = new AddQuestionRequest(
                "this is the body",
                Arrays.asList("answer1", "answer2"),
                "do it",
                "life",
                "guyman",
                "1"
        );
        ResponseEntity<Question> response = template.exchange(
                baseUrl.toString() + "/questions",
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