package com.prodigy.domain.questions.command;

import com.prodigy.domain.common.service.AbstractCommand;
import com.prodigy.domain.questions.Question;
import com.prodigy.domain.questions.data.QuestionRepository;
import com.prodigy.domain.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

@Component
public class RecommendQuestionsCommand extends AbstractCommand<Question, AddQuestionRequest> {

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
