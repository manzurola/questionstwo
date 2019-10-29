package com.prodigy.domain.answer;

import com.prodigy.common.Id;
import com.prodigy.domain.questions.domain.Question;

import java.util.List;

public interface GetAnswerService {
    Answer getById(Id<Answer> id);

    List<Answer> getByQuestionId(Id<Question> questionId);
}
