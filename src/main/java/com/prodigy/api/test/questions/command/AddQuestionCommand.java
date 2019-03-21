package com.prodigy.api.test.questions.command;

import com.prodigy.api.test.common.service.AbstractCommand;
import com.prodigy.api.test.questions.data.QuestionRepository;
import com.prodigy.api.test.questions.request.AddQuestionRequest;
import com.prodigy.api.test.questions.Question;
import org.springframework.stereotype.Component;

@Component
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
