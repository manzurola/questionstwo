package com.prodigy.infrastructure.repository;

import com.prodigy.domain.Answer;
import com.prodigy.domain.repository.AnswerRepository;
import com.prodigy.domain.Id;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAnswerRepository implements AnswerRepository {

    private final Map<Id<Answer>, Answer> data = new ConcurrentHashMap<>();

    @Override
    public void add(Answer answer) {
        data.put(answer.getId(), answer);
    }

    @Override
    public Answer getByID(Id<Answer> id) {
        return data.get(id);
    }
}
