package com.prodigy.api.exercises;

import com.prodigy.api.common.Id;
import com.prodigy.api.common.NotFoundException;

import java.util.List;
import java.util.Optional;

public class InMemoryExerciseRepository implements ExerciseRepository {

    private final List<Exercise> data;

    public InMemoryExerciseRepository(List<Exercise> data) {
        this.data = data;
    }

    @Override
    public Exercise get(Id<Exercise> id) throws Exception {
        Optional<Exercise> result = data.stream().filter(exercise -> exercise.getId().equals(id)).findFirst();
        if (!result.isPresent()) {
            throw new NotFoundException("Exercise with id " + id + " does not exist");
        }
        return result.get();
    }

    @Override
    public List<Exercise> getAll() throws Exception {
        return data;
    }

    @Override
    public Exercise add(Exercise exercise) throws Exception {
        data.add(exercise);
        return exercise;
    }
}
