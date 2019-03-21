package com.prodigy.api.test.review;

import com.prodigy.api.test.common.Id;

public interface ReviewRepository {

    Review add(Review review);

    Review get(Id<Review> id);
}
