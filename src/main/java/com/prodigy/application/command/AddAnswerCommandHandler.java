package com.prodigy.application.command;

import com.prodigy.domain.repository.AnswerRepository;
import com.prodigy.domain.Answer;
import com.prodigy.domain.AnswerFactory;

public class AddAnswerCommandHandler implements CommandHandler<AddAnswerCommand> {

    private final AnswerFactory factory;
    private final AnswerRepository repository;

    public AddAnswerCommandHandler(AnswerFactory factory, AnswerRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public void handle(AddAnswerCommand command) {
        Answer answer = new Answer(command.questionId(), command.input());
        repository.add(answer);
    }
}
