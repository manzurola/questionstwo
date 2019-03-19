package com.prodigy.api.answers;

import com.prodigy.api.common.ElasticsearchDataStore;
import org.springframework.stereotype.Component;

@Component
public class ElastisearchAnswerRepository implements AnswerRepository {

    private static final String index = "answers";
    private static final String type = "answer";
    private final ElasticsearchDataStore dataStore;

    public ElastisearchAnswerRepository(ElasticsearchDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Answer addAnswer(Answer answer) {
        dataStore.add(index, type, answer.getId(), answer);
        return answer;
    }
}
