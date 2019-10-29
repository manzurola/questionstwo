package com.prodigy.domain.questions.service;

import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.domain.questions.domain.Question;
import com.prodigy.domain.questions.database.QuestionRepository;
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
