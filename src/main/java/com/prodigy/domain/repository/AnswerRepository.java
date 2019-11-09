package com.prodigy.domain.repository;

import com.prodigy.domain.Answer;
import com.prodigy.domain.Id;

public interface AnswerRepository {

    void add(Answer answer);

    Answer getByID(Id<Answer> id);
}
