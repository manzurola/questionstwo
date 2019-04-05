package com.prodigy.api.exercises.utils;

import com.prodigy.api.exercises.Exercise;
import com.prodigy.api.questions.Question;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface ExerciseReader {

    List<Exercise> readAll() throws IOException;

}
