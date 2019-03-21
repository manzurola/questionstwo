package com.prodigy.api.test.review;

import com.prodigy.api.test.common.service.AbstractCommand;

public class AddReviewCommand extends AbstractCommand<Review, AddReviewRequest> {

    private final ReviewRepository repository;

    public AddReviewCommand(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Review doExecute(AddReviewRequest request) throws Exception {
        Review review = Review.builder()
                .answerId(request.getAnswerId())
                .score(request.getScore())
                .comment(request.getComment())
                .reviewerId(request.getReviewerId())
                .build();
        return repository.add(review);
    }
}
