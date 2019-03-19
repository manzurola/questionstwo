package com.prodigy.api.questions.tools;

import com.prodigy.api.questions.Question;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface QuestionReader<T extends Question> extends Closeable, Iterable<T> {

    List<T> readAll() throws IOException;

    T readNext() throws IOException;

    String getParserVersion();
}
