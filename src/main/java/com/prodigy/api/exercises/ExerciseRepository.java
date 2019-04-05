package com.prodigy.api.exercises;


import com.prodigy.api.common.Id;

import java.util.List;

/**
 * Created by guym on 16/05/2017.
 */
public interface ExerciseRepository {

    Exercise get(Id<Exercise> id) throws Exception;

    List<Exercise> getAll() throws Exception;

    Exercise add(Exercise exercise) throws Exception;
}
