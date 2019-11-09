package com.prodigy.application;

import com.prodigy.application.query.QueryHandler;
import com.prodigy.domain.Question;
import com.prodigy.domain.repository.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class GetQuestionQueryHandler implements QueryHandler<GetQuestionByIdQuery, Question> {

    private final QuestionRepository repository;

    public GetQuestionQueryHandler(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question handle(GetQuestionByIdQuery query) {
        return repository.get(query.getId());
    }
}
