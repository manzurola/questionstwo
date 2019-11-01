package com.prodigy.domain;

import com.prodigy.domain.Question;

public interface Reviewer {

    Review reviewAnswer(String answer, Question question);
}
