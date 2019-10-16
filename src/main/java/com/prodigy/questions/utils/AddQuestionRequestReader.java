package com.prodigy.questions.utils;

import com.prodigy.questions.request.AddQuestionRequest;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * Created by guym on 21/05/2017.
 */
public interface AddQuestionRequestReader extends Closeable {

    List<AddQuestionRequest> readAll() throws IOException;

    AddQuestionRequest readNext() throws IOException;
}
