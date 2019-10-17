package com.prodigy.questions.command;

import com.prodigy.common.service.AbstractCommand;
import com.prodigy.questions.data.QuestionRepository;
import com.prodigy.questions.request.AddQuestionRequest;
import com.prodigy.questions.Question;
import org.springframework.stereotype.Component;

@Component
public class AddQuestionCommand extends AbstractCommand<Question, AddQuestionRequest> {

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
