package com.prodigy.api.questions.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.questions.Question;
import com.prodigy.api.questions.data.QuestionRepository;
import com.prodigy.api.questions.request.AddQuestionRequest;
import org.springframework.stereotype.Component;

@Component
public class RecommendQuestionsCommand extends AbstractCommand<Question, AddQuestionRequest> {

    private final QuestionRepository repository;


    public RecommendQuestionsCommand(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Question doExecute(AddQuestionRequest request) throws Exception {
        Question question = Question.builder()
                .body(request.getBody())
                .answerKey(request.getAnswerKey())
                .build();
        return repository.add(question);
    }
}
