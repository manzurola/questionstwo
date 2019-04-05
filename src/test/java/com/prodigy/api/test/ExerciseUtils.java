package com.prodigy.api.test;

import com.prodigy.api.exercises.AddExerciseRequest;
import com.prodigy.api.exercises.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseUtils {

    private final QuestionUtils questionUtils;

    public ExerciseUtils(QuestionUtils questionUtils) {
        this.questionUtils = questionUtils;
    }

    public Exercise.Builder newExerciseFromRequest(AddExerciseRequest request) {
        return Exercise.builder()
                .title(request.getTitle())
                .questions(request.getQuestions());
    }
}
