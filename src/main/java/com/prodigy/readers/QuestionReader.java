package com.prodigy.readers;

import com.prodigy.service.AddQuestionRequest;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface QuestionReader extends Closeable {

    List<AddQuestionRequest> readAll() throws IOException;

    AddQuestionRequest readNext() throws IOException;
}
