package com.prodigy.domain;

import com.prodigy.domain.diff.Operation;
import com.prodigy.domain.diff.SentenceDiff;
import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.nlp.*;
import com.prodigy.domain.diff.Diff;

import java.util.List;

public class SmartReviewer implements Reviewer {

    private final SentenceFactory sentenceFactory;
    private final SentenceDiffChecker diffChecker;

    public SmartReviewer(SentenceFactory sentenceFactory, SentenceDiffChecker diffChecker) {
        this.sentenceFactory = sentenceFactory;
        this.diffChecker = diffChecker;
    }

    @Override
    public Review reviewAnswer(String answer, Question question) {
        Sentence source = sentenceFactory.fromString(answer);
        double top = -1;
        double score = 0;
        SentenceDiff bestMatch = null;
        for (String target : question.getAnswerKey()) {
            SentenceDiff diff = diffChecker.diffSourceAndTarget(source, sentenceFactory.fromString(target));
            score = scoreDiff(diff);
            if (score > top) {
                bestMatch = diff;
            }
        }

        return Review.newBuilder()
                .diff(bestMatch)
                .isCorrect(score == 1)
                .score(score)
                .build();
    }

    private SentenceDiff findBestMatch(String answer, List<String> answerKey) {
        double top = -1;
        double score = 0;
        SentenceDiff bestMatch = null;
        for (String target : answerKey) {
            SentenceDiff diff = diffChecker.diffSourceAndTarget(sentenceFactory.fromString(answer), sentenceFactory.fromString(target));
            score = scoreDiff(diff);
            if (score > top) {
                bestMatch = diff;
            }
        }

        return bestMatch;
    }

    private double scoreDiff(SentenceDiff diff) {
        int totalWordCount = diff.words().size();
        int correctWordCount = totalWordCount;
        for (Diff<Word> wordDiff : diff.words()) {
            correctWordCount -= wordDiff.operation().equals(Operation.EQUAL) ? 0 : 1;
        }
        return correctWordCount / totalWordCount;
    }
}
