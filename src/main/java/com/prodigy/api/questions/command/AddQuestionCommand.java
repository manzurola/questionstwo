package com.prodigy.api.questions.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.request.AddQuestionRequest;
import com.prodigy.api.questions.Question;

public class AddQuestionCommand extends AbstractCommand<Question, AddQuestionRequest> {

    private final QuestionRepository repository;

    public AddQuestionCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(AddQuestionRequest request) throws Exception {
        Question question = Question.builder()
                .body(request.getBody())
                .answerKey(request.getAnswerKey())
                .instructions(request.getInstructions())
                .subject(request.getSubject())
                .source(request.getSource())
                .version(request.getVersion())
                .build();
        return repository.add(question);
    }
}
