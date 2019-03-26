package com.prodigy.api.review.data;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.Id;
import com.prodigy.api.review.Review;

public interface ReviewRepository {

    Review add(Review review);

    Review get(Id<Review> id);

    Review getForAnswer(Id<Answer> answerId);
}
