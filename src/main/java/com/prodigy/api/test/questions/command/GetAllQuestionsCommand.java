package com.prodigy.api.test.questions.command;

import com.prodigy.api.test.common.service.AbstractCommand;
import com.prodigy.api.test.questions.data.QuestionRepository;
import com.prodigy.api.test.questions.Question;
import com.prodigy.api.test.questions.request.GetAllQuestionsRequest;
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
