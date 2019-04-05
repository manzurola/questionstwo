package com.prodigy.api.test;

import com.prodigy.api.exercises.AddExerciseRequest;
import com.prodigy.api.questions.Exercise;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class ExerciseUtils {

    private final QuestionUtils questionUtils;

    public ExerciseUtils(QuestionUtils questionUtils) {
        this.questionUtils = questionUtils;
    }

    public Exercise.Builder newExerciseFromRequest(AddExerciseRequest request) {
        return Exercise.builder()
                .title(request.getTitle())
                .source(request.getSource())
                .questions(request.getQuestionIds());
    }
}
