package com.prodigy.answers.data;

import com.prodigy.answers.Answer;
import com.prodigy.common.data.DataStore;

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
