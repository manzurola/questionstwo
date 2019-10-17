package com.prodigy.questions.command;

import com.prodigy.common.service.AbstractCommand;
import com.prodigy.questions.Question;
import com.prodigy.questions.data.QuestionRepository;
import com.prodigy.questions.request.GetQuestionRequest;
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
