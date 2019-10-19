package com.prodigy.questions.service;

import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.questions.domain.Question;
import com.prodigy.questions.database.QuestionRepository;
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
