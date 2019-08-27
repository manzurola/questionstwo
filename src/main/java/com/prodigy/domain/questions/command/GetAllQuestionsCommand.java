package com.prodigy.domain.questions.command;

import com.prodigy.domain.common.service.AbstractCommand;
import com.prodigy.domain.questions.data.QuestionRepository;
import com.prodigy.domain.questions.Question;
import com.prodigy.domain.questions.request.GetAllQuestionsRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
