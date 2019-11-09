package com.prodigy.application.query;

import com.prodigy.domain.repository.QuestionRepository;
import com.prodigy.domain.Question;

import java.util.List;

public class GetQuestionsQueryHandler implements QueryHandler<GetQuestionsQuery, List<Question>> {

    private final QuestionRepository repository;

    public GetQuestionsQueryHandler(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Question> handle(GetQuestionsQuery query) {
        return repository.getAll();
    }
}
