package com.prodigy.api.questions.service;

import com.prodigy.api.questions.data.QuestionRepository;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question get(String id) {
        try {
            return repository.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> getAll() {
        try {
            return repository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Question add(Question question) throws Exception {
        return repository.add(question);
    }

    @Override
    public Question update(Question question) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
