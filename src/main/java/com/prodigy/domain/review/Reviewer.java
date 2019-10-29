package com.prodigy.domain.review;

import com.prodigy.domain.questions.domain.Question;

public interface Reviewer {

    Review reviewAnswer(String answer, Question question);
}
