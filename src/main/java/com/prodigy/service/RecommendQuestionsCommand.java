package com.prodigy.questions.service;

import com.prodigy.service.common.AbstractServiceCommand;
import com.prodigy.domain.Question;
import com.prodigy.database.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class RecommendQuestionsCommand extends AbstractServiceCommand<Question, AddQuestionRequest> {

    private final QuestionRepository repository;


    public RecommendQuestionsCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(AddQuestionRequest request) throws Exception {
        Question question = Question.newBuilder()
                .withBody(request.getBody())
                .withAnswerKey(request.getAnswerKey())
                .build();
        return repository.add(question);
    }
}
