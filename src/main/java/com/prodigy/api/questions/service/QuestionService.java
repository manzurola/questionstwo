package com.prodigy.api.questions.service;

import java.util.List;

public interface QuestionService {

    Question get(String id);

    List<Question> getAll();

    Question add(Question question) throws Exception;

    Question update(Question question);

    void delete(String id);
}
