package com.prodigy.questions.service;

import com.prodigy.common.service.AbstractServiceCommand;
import com.prodigy.questions.database.QuestionRepository;
import com.prodigy.questions.domain.Question;
import org.springframework.stereotype.Component;

@Component
public class AddQuestionCommand extends AbstractServiceCommand<Question, AddQuestionRequest> {

    private final QuestionRepository repository;

    public AddQuestionCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(AddQuestionRequest request) throws Exception {
        Question question = Question.newBuilder()
                .withBody(request.getBody())
                .withAnswerKey(request.getAnswerKey())
                .withInstructions(request.getInstructions())
                .build();
        return repository.add(question);
    }
}
