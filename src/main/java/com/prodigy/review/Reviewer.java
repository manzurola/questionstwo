package com.prodigy.review;

import com.prodigy.questions.Question;

public interface Reviewer {

    Review reviewAnswer(String answer, Question question);
}
