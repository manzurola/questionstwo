package com.prodigy.infrastructure.repository;

import com.prodigy.domain.Answer;
import com.prodigy.domain.repository.AnswerRepository;
import com.prodigy.domain.Id;

public class ElastisearchAnswerRepository implements AnswerRepository {

    private static final String index = "answers";
    private static final String type = "answer";
    private final DataStore dataStore;

    public ElastisearchAnswerRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void add(Answer answer) {
        dataStore.add(index, type, answer.getId(), answer);
    }

    @Override
    public Answer getByID(Id<Answer> id) {
        return dataStore.get(index, type, id, Answer.class);
    }
}
