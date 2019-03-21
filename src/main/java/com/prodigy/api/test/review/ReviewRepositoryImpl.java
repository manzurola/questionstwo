package com.prodigy.api.test.review;

import com.prodigy.api.test.common.ElasticsearchDataStore;
import com.prodigy.api.test.common.Id;
import org.springframework.stereotype.Component;

@Component
public class ReviewRepositoryImpl implements ReviewRepository {

    private static final String index = "reviews";
    private static final String type = "review";
    private final ElasticsearchDataStore dataStore;

    public ReviewRepositoryImpl(ElasticsearchDataStore dataStore) {
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
}
