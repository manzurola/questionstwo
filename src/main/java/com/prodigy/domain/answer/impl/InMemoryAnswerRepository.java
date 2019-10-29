package com.prodigy.domain.answer.impl;

import com.prodigy.domain.answer.Answer;
import com.prodigy.common.Id;
import com.prodigy.domain.answer.AnswerRepository;
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
