package com.prodigy.api.answers;

import com.prodigy.api.common.service.AbstractCommand;
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
                .userId(request.getUserId())
                .questionId(request.getQuestionId())
                .input(request.getAnswer())
                .build();
        return repository.addAnswer(answer);
    }
}
