package com.prodigy.api;


import com.prodigy.api.answers.Answer;
import com.prodigy.api.common.Id;
import com.prodigy.api.common.service.Result;
import com.prodigy.api.common.service.ServiceExecutor;
import com.prodigy.api.review.command.GetReviewCommand;
import com.prodigy.api.review.request.GetReviewRequest;
import com.prodigy.api.review.Review;
import org.springframework.web.bind.annotation.*;


/**
 * Created by guym on 17/05/2017.
 */
@RestController
public class ReviewController {

    private final ServiceExecutor serviceExecutor;

    public ReviewController(ServiceExecutor serviceExecutor) {
        this.serviceExecutor = serviceExecutor;
    }

//    @GetMapping("/reviews/{id}")
//    public Review getReview(@PathVariable Id<Review> id) {
//        Result<Review> result = serviceExecutor.execute(GetReviewCommand.class, new GetReviewRequest(id));
//        return result.getData();
//    }

    @GetMapping("/reviews")
    public Review getReview(@RequestParam Id<Answer> answerId) {
        Result<Review> result = serviceExecutor.execute(GetReviewCommand.class, new GetReviewRequest(answerId));
        return result.getData();
    }

}
