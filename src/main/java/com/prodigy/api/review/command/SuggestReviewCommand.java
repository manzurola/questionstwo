package com.prodigy.api.review.command;

import com.prodigy.api.common.service.AbstractCommand;
import com.prodigy.api.review.request.AddReviewRequest;
import com.prodigy.api.review.Score;
import com.prodigy.api.review.request.SuggestReviewRequest;
import com.prodigy.nlp.Sentence;
import com.prodigy.nlp.SentenceParser;
import com.prodigy.nlp.diff.SentenceDiff;
import com.prodigy.nlp.diff.SentenceDiffCheck;
import com.prodigy.nlp.diff.TextDiff;
import com.prodigy.nlp.diff.WordDiff;
import org.springframework.stereotype.Component;

@Component
public class SuggestReviewCommand extends AbstractCommand<AddReviewRequest, SuggestReviewRequest> {

    private final SentenceParser parser;
    private final SentenceDiffCheck diffCheck;

    public SuggestReviewCommand(SentenceParser parser, SentenceDiffCheck diffCheck) {
        this.parser = parser;
        this.diffCheck = diffCheck;
    }

    @Override
    protected AddReviewRequest doExecute(SuggestReviewRequest request) throws Exception {

        Sentence source = parser.parse(request.getAnswer().getInput());
        Sentence target = parser.parse(request.getQuestion().getAnswerKey().get(0));
        SentenceDiff sentenceDiff = diffCheck.check(source, target);

        boolean hasDiff = false;
        for (WordDiff wordDiff : sentenceDiff.getDiff()) {
            if (!TextDiff.Operation.EQUAL.equals(wordDiff.diff().getOperation())) {
                hasDiff = true;
                break;
            }

        }

        int points = 0;
        if (!hasDiff) {
            points = 1;
        }

        return new AddReviewRequest(request.getAnswer().getId(), new Score(points), null, null, sentenceDiff);
    }
}
