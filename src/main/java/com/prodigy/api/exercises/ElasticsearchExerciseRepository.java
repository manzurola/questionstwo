package com.prodigy.api.exercises;

import com.prodigy.api.common.DataStore;
import com.prodigy.api.common.Id;

import java.util.List;

public class ElasticsearchExerciseRepository implements ExerciseRepository {

    public static final String index = "exercises_en";
    public static final String type = "exercise";
    private final DataStore dataStore;

    public ElasticsearchExerciseRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Exercise get(Id<Exercise> id) throws Exception {
        return dataStore.get(index, type, id, Exercise.class);
    }

    @Override
    public List<Exercise> getAll() throws Exception {
        return dataStore.getAll(index, type, Exercise.class);
    }

    @Override
    public Exercise add(Exercise exercise) throws Exception {
        dataStore.add(index, type, exercise.getId(), exercise);
        return dataStore.get(index, type, exercise.getId(), Exercise.class);
    }
}
