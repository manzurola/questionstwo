package com.prodigy.api.review.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.api.review.Review;
import com.prodigy.api.review.data.ReviewRepository;
import com.prodigy.nlp.diff.TextDiffCalculator;
import org.springframework.stereotype.Component;

@Component
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
                .sentenceDiff(request.getDiff())
                .build();
        return repository.add(review);
    }
}
