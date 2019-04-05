package com.prodigy.api.exercises;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.questions.Exercise;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllExercisesCommand extends AbstractCommand<List<Exercise>, GetAllExercisesRequest> {

    private final ExerciseRepository repository;

    public GetAllExercisesCommand(ExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Exercise> doExecute(GetAllExercisesRequest request) throws Exception {
        return repository.getAll();
    }
}
