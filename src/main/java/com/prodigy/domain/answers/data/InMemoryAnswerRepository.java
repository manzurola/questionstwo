package com.prodigy.domain.answers.data;

import com.prodigy.domain.answers.Answer;
import com.prodigy.domain.common.Id;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAnswerRepository implements AnswerRepository {

    private final Map<Id<Answer>, Answer> data = new ConcurrentHashMap<>();

    @Override
    public Answer addAnswer(Answer answer) {
        data.put(answer.getId(), answer);
        return data.get(answer.getId());
    }
}