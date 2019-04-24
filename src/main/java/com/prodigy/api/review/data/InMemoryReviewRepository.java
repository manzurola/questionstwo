package com.prodigy.api.review.data;

import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.Id;
import com.prodigy.api.review.Review;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryReviewRepository implements ReviewRepository {

    private final Map<Id<Review>, Review> data = new ConcurrentHashMap<>();

    @Override
    public Review add(Review review) {
        data.put(review.getId(), review);
        return review;
    }

    @Override
    public Review get(Id<Review> id) {
        return data.get(id);
    }

    @Override
    public Review getForAnswer(Id<Answer> answerId) {
        return data.entrySet().stream()
                .filter(entry -> entry.getValue().getAnswerId().equals(answerId))
                .findFirst().get().getValue();
    }
}
