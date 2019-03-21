package com.prodigy.api.test.answers;

import com.prodigy.api.test.common.service.AbstractCommand;
import org.springframework.stereotype.Component;

@Component
public class SubmitAnswerCommand extends AbstractCommand<Answer, SubmitAnswerRequest> {

    private final AnswerRepository repository;

    public SubmitAnswerCommand(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Answer doExecute(SubmitAnswerRequest request) throws Exception {
        Answer answer = Answer.builder()
                .setUserId(request.getUserId())
                .setQuestionId(request.getQuestionId())
                .setAnswer(request.getAnswer())
                .build();
        return repository.addAnswer(answer);
    }
}
