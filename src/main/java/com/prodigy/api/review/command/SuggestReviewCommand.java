package com.prodigy.api.review.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.api.review.request.SuggestReviewRequest;
import com.prodigy.api.review.reviewer.Reviewer;
import com.prodigy.nlp.diff.SentenceDiffCheck;
import org.springframework.stereotype.Component;

@Component
public class SuggestReviewCommand extends AbstractCommand<AddReviewRequest, SuggestReviewRequest> {

    private final SentenceDiffCheck diffCheck;
    private final Reviewer reviewer;

    public SuggestReviewCommand(SentenceDiffCheck diffCheck, Reviewer reviewer) {
        this.diffCheck = diffCheck;
        this.reviewer = reviewer;
    }

    @Override
    protected AddReviewRequest doExecute(SuggestReviewRequest request) throws Exception {

//        Sentence source = parser.parse(request.getAnswer().getInput());
//        Sentence target = parser.parse(request.getQuestion().getAnswerKey().get(0));
//        SentenceDiff sentenceDiff = diffCheck.check(source, target);

//        SentenceDiff sentenceDiff = diffCheck.check(request.getAnswer().getInput(), request.getQuestion().getAnswerKey().get(0));
//
//        boolean hasDiff = false;
//        for (TextDiff wordDiff : sentenceDiff.getTextDiffs()) {
//            if (!TextDiff.Operation.EQUAL.equals(wordDiff.getOperation())) {
//                hasDiff = true;
//                break;
//            }
//
//        }
//
//        int points = 0;
//        if (!hasDiff) {
//            points = 1;
//        }
//
//        return new AddReviewRequest(request.getAnswer().getId(), new Score(points), null, null, sentenceDiff, null);

        return reviewer.review(request.getAnswer());
    }

}
