package com.prodigy.api.review.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.api.review.Score;
import com.prodigy.api.review.request.SuggestReviewRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestReviewCommand extends AbstractCommand<AddReviewRequest, SuggestReviewRequest> {

    @Override
    protected AddReviewRequest doExecute(SuggestReviewRequest request) throws Exception {
        String userAnswer = request.getAnswer().getInput();
        List<String> answerKey = request.getQuestion().getAnswerKey();
        int points = 0;
        for (String s : answerKey) {
            if (s.equals(userAnswer)) {
                points = 100;
                break;
            }
        }

        return new AddReviewRequest(request.getAnswer().getId(), new Score(points), null, null);
    }
}
