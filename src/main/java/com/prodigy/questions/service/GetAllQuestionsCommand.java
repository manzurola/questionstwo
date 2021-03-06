package com.prodigy.questions.service;

import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.questions.database.QuestionRepository;
import com.prodigy.questions.domain.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllQuestionsCommand extends AbstractServiceCommand<List<Question>, GetAllQuestionsRequest> {

    private final QuestionRepository repository;

    public GetAllQuestionsCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<Question> doExecute(GetAllQuestionsRequest request) throws Exception {
        return repository.getAll();
    }
}
