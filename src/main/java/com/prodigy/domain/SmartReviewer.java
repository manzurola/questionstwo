package com.prodigy.domain;

import com.prodigy.diff.Operation;
import com.prodigy.diff.SentenceDiff;
import com.prodigy.diff.SentenceDiffCheck;
import com.prodigy.nlp.*;
import com.prodigy.diff.Diff;

import java.util.List;

public class SmartReviewer implements Reviewer {

    private final SentenceFactory sentenceFactory;
    private final SentenceDiffCheck diffChecker;

    public SmartReviewer(SentenceFactory sentenceFactory, SentenceDiffCheck diffChecker) {
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
