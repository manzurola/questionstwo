package com.prodigy.api.questions.service;

import com.prodigy.api.questions.data.QuestionRepository;

import javax.validation.Validator;

public class QuestionCommandFactoryImpl implements QuestionCommandFactory {

    private QuestionRepository repository;

    public QuestionCommandFactoryImpl(QuestionRepository repository) {

        this.repository = repository;
    }

    @Override
    public AddQuestionCommand createAdd() {
        return new AddQuestionCommand(repository);
    }
}
