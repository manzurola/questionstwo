package com.prodigy.application.query;

import com.prodigy.domain.repository.QuestionRepository;
import com.prodigy.domain.Question;

public class GetQuestionByIdQueryHandler implements QueryHandler<GetQuestionByIdQuery, Question> {

    private final QuestionRepository repository;

    public GetQuestionByIdQueryHandler(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question handle(GetQuestionByIdQuery query) {
        return repository.get(query.questionId());
    }
}
