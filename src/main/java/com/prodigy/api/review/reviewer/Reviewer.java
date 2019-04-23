package com.prodigy.api.review.reviewer;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.review.request.AddReviewRequest;

public interface Reviewer {

    AddReviewRequest review(Answer answer);
}
