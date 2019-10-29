package com.prodigy.domain.answer.impl;

import com.prodigy.domain.answer.Answer;
import com.prodigy.common.data.DataStore;
import com.prodigy.domain.answer.AnswerRepository;

public class ElastisearchAnswerRepository implements AnswerRepository {

    private static final String index = "answers";
    private static final String type = "answer";
    private final DataStore dataStore;

    public ElastisearchAnswerRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Answer addAnswer(Answer answer) {
        dataStore.add(index, type, answer.getId(), answer);
        return answer;
    }
}
