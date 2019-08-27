package com.prodigy.domain.questions.command;

import com.prodigy.domain.common.service.AbstractCommand;
import com.prodigy.domain.questions.Question;
import com.prodigy.domain.questions.data.QuestionRepository;
import com.prodigy.domain.questions.request.GetQuestionRequest;
import org.springframework.stereotype.Component;

@Component
public class GetQuestionCommand extends AbstractCommand<Question, GetQuestionRequest> {

    private final QuestionRepository repository;

    public GetQuestionCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(GetQuestionRequest request) throws Exception {
        return repository.get(request.getId());
    }
}
