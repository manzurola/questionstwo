package com.prodigy.service;

import com.prodigy.domain.Id;
import com.prodigy.domain.Answer;
import com.prodigy.domain.Question;

import java.util.List;

public interface GetAnswerService {
    Answer getById(Id<Answer> id);

    List<Answer> getByQuestionId(Id<Question> questionId);
}
