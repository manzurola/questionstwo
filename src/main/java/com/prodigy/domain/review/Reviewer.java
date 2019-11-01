package com.prodigy.domain.review;

import com.prodigy.domain.Question;
import com.prodigy.domain.Review;

public interface Reviewer {

    Review reviewAnswer(String answer, Question question);
}
