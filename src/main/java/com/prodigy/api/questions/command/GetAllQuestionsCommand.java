package com.prodigy.api.questions.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.request.GetAllQuestionsRequest;

import java.util.List;

public class GetAllQuestionsCommand extends AbstractCommand<List<Question>, GetAllQuestionsRequest> {

    private final QuestionRepository repository;

    public GetAllQuestionsCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Question> doExecute(GetAllQuestionsRequest request) throws Exception {
        return repository.getAll();
    }
}
