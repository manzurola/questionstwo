package com.prodigy.service;

import com.prodigy.service.common.AbstractServiceCommand;
import com.prodigy.database.QuestionRepository;
import com.prodigy.domain.Question;
import org.springframework.stereotype.Component;

@Component
public class AddQuestionCommand extends AbstractServiceCommand<Question, AddQuestionRequest> {

    private final QuestionRepository repository;

    public AddQuestionCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(AddQuestionRequest request) throws Exception {
        return repository.add(request.toQuestion());
    }
}
