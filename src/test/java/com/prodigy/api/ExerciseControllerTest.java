package com.prodigy.api;

import com.prodigy.api.env.EndToEndTest;
import com.prodigy.api.exercises.AddExerciseRequest;
import com.prodigy.api.questions.Exercise;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.test.AddQuestionApiCall;
import com.prodigy.api.test.ExerciseUtils;
import com.prodigy.api.test.QuestionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ExerciseControllerTest extends EndToEndTest {

    @Autowired
    private QuestionUtils questionUtils;
    @Autowired
    private ExerciseUtils exericseUtils;

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
    public void addExercise() {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ResponseEntity<Question> response = new AddQuestionApiCall()
                    .run(questionUtils.randomAddQuestionRequest(), template, baseUrl);
            questions.add(response.getBody());
        }

        AddExerciseRequest addExerciseRequest = new AddExerciseRequest(
                "Put the sentences into the plural",
                "no source",
                questions.stream().map(question -> question.getId()).collect(Collectors.toList())
        );
        ResponseEntity<Exercise> response = template.exchange(
                baseUrl.toString() + "/exercises",
                HttpMethod.POST,
                new HttpEntity<>(addExerciseRequest),
                new ParameterizedTypeReference<Exercise>() {
                });

        Exercise actual = response.getBody();
        Exercise expected = exericseUtils.newExerciseFromRequest(addExerciseRequest)
                .id(actual.getId())
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void getQuestion() {

        AddQuestionRequest request = questionUtils.randomAddQuestionRequest();
        ResponseEntity<Question> response = new AddQuestionApiCall().run(request, template, baseUrl);
        Question actual = response.getBody();

    }
}