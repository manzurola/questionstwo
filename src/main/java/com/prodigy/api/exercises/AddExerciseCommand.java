package com.prodigy.api.exercises;

import com.prodigy.api.common.service.AbstractCommand;
import org.springframework.stereotype.Component;

@Component
public class AddExerciseCommand extends AbstractCommand<Exercise, AddExerciseRequest> {

    private final ExerciseRepository repository;

    public AddExerciseCommand(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Exercise doExecute(AddExerciseRequest request) throws Exception {
        Exercise exercise = Exercise.builder()
                .title(request.getTitle())
                .questions(request.getQuestions())
                .build();
        return repository.add(exercise);
    }
}
