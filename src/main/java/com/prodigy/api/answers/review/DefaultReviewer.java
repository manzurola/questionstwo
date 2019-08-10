package com.prodigy.api.answers.review;

import com.prodigy.api.questions.Question;
import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.DiffCalculator;
import com.prodigy.core.nlp.SentenceParser;

import java.util.List;

public class DefaultReviewer implements Reviewer {

    private final SentenceParser sentenceParser;
    private final DiffCalculator diffCalculator;

    public DefaultReviewer(SentenceParser sentenceParser, DiffCalculator diffCalculator) {
        this.sentenceParser = sentenceParser;
        this.diffCalculator = diffCalculator;
    }

    @Override
    public Review reviewAnswer(String answer, Question question) {

        // tokenize and tag words
        List<Word> parsedAnswer = sentenceParser.parse(answer);
        List<Word> parsedTarget = sentenceParser.parse(question.getAnswerKey().get(0));
        // lower case (?)
        // get diff

        List<Diff<Word>> diff = diffCalculator.getDiff(parsedAnswer, parsedTarget);

        // score diff
        boolean isCorrect = true;
        for (Diff<Word> wordDiff : diff) {
            if (wordDiff.operation().equals(Diff.Operation.INSERT) || wordDiff.operation().equals(Diff.Operation.DELETE)) {
                isCorrect = false;
            }
        }

        // return review

        return Review.newBuilder()
                .diff(diff)
                .isCorrect(isCorrect)
                .build();
    }

}
