package com.prodigy.api.questions.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.request.GetAllQuestionsRequest;
import com.prodigy.api.questions.request.GetQuestionRequest;
import org.springframework.stereotype.Component;

import java.util.List;

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
