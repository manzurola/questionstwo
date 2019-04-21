package com.prodigy.api.review.data;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.DataStore;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.NotFoundException;
import com.prodigy.api.review.Review;
import org.springframework.stereotype.Component;

import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepository {

    private static final String index = "reviews";
    private static final String type = "review";
    private final DataStore dataStore;

    public ReviewRepositoryImpl(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Review add(Review review) {
        dataStore.add(index, type, review.getId(), review);
        return review;
    }

    @Override
    public Review get(Id<Review> id) {
        return dataStore.get(index, type, id, Review.class);
    }

    @Override
    public Review getForAnswer(Id<Answer> answerId) {
        List<Review> results = dataStore.getByProperty(index, type, Review.class, "answerId", answerId.getId());
        if (results.isEmpty()) throw new NotFoundException();
        return results.get(0); // first result until multiple reviews are supported
    }
}
