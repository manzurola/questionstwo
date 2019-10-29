package com.prodigy.domain.answer;

import com.prodigy.common.Id;
import com.prodigy.domain.questions.domain.Question;

public interface AnswerFactory {
    Answer create(Id<Question> questionId, String input);
    Answer create(Id<Answer> id, Id<Question> questionId, String input);
}
