package com.prodigy.api.questions.service;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.common.service.CommandExecutionExecption;
import com.prodigy.api.questions.data.QuestionRepository;

public class AddQuestionCommand extends AbstractCommand<Question, AddQuestionRequest> {

    private final QuestionRepository repository;

    public AddQuestionCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question doExecute(AddQuestionRequest request) {
        Question question = Question.builder()
                .body(request.getBody())
                .answerKey(request.getAnswerKey())
                .instructions(request.getInstructions())
                .subject(request.getSubject())
                .source(request.getSource())
                .version(request.getVersion())
                .build();
        try {
            return repository.add(question);
        } catch (Exception e) {
            throw new CommandExecutionExecption(e);
        }
    }
}
