package com.prodigy.service;

import com.prodigy.service.common.AbstractServiceCommand;
import com.prodigy.domain.Question;
import com.prodigy.database.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class GetQuestionCommand extends AbstractServiceCommand<Question, GetQuestionRequest> {

    private final QuestionRepository repository;

    public GetQuestionCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(GetQuestionRequest request) throws Exception {
        return repository.get(request.getId());
    }
}
