package com.prodigy.api.answers.review;

import com.prodigy.api.questions.Question;
import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.SentenceDiffChecker;
import com.prodigy.core.nlp.SentenceParser;

import java.util.List;

public class Reviewer {

    private final SentenceParser parser;
    private final SentenceDiffChecker diffChecker;

    public Reviewer(SentenceParser parser, SentenceDiffChecker diffChecker) {
        this.parser = parser;
        this.diffChecker = diffChecker;
    }

    public Review reviewAnswer(String answer, Question question) {

        // tokenize and tag words
        List<Word> parsedAnswer = parser.parse(answer);
        List<Word> parsedTarget = parser.parse(question.getAnswerKey().get(0));
        // lower case (?)
        // get diff

        List<Diff<Word>> diff = diffChecker.checkDiff(parsedAnswer, parsedTarget);

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
