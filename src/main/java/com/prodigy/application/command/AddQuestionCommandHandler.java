package com.prodigy.application.command;

import com.prodigy.domain.repository.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class AddQuestionCommandHandler implements CommandHandler<AddQuestionCommand> {

    private final QuestionRepository repository;

    public AddQuestionCommandHandler(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(AddQuestionCommand command)  {
        repository.add(command.toQuestion());
    }
}
