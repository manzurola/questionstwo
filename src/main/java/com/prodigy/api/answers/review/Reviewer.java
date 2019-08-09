package com.prodigy.api.answers.review;

import com.prodigy.api.questions.Question;

public interface Reviewer {

    Review reviewAnswer(String answer, Question question);
}
