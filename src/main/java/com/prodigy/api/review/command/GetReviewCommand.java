package com.prodigy.api.review.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.review.Review;
import com.prodigy.api.review.data.ReviewRepository;
import com.prodigy.api.review.request.GetReviewRequest;
import org.springframework.stereotype.Component;

@Component
public class GetReviewCommand extends AbstractCommand<Review, GetReviewRequest> {

    private final ReviewRepository repository;

    public GetReviewCommand(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Review doExecute(GetReviewRequest request) throws Exception {
        return repository.getForAnswer(request.getAnswerId());
    }
}
