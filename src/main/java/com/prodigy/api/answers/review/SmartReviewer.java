package com.prodigy.api.answers.review;

import com.prodigy.api.questions.Question;
import com.prodigy.core.Sentence;
import com.prodigy.core.SentenceDiff;
import com.prodigy.core.SentenceFactory;
import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.SentenceDiffChecker;

public class SmartReviewer implements Reviewer {

    private final SentenceFactory sentenceFactory;
    private final SentenceDiffChecker diffChecker;

    public SmartReviewer(SentenceFactory sentenceFactory, SentenceDiffChecker diffChecker) {
        this.sentenceFactory = sentenceFactory;
        this.diffChecker = diffChecker;
    }

    @Override
    public Review reviewAnswer(String answer, Question question) {
        Sentence source = sentenceFactory.getSentence(answer);
        double top = -1;
        double score = 0;
        SentenceDiff bestMatch = null;
        for (String target : question.getAnswerKey()) {
            SentenceDiff diff = diffChecker.checkDiff(source, sentenceFactory.getSentence(target));
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

    private double scoreDiff(SentenceDiff diff) {
        int size = diff.wordDiff().size();
        int correct = size;
        for (Diff<Word> wordDiff : diff.wordDiff()) {
            correct -= wordDiff.operation().equals(Diff.Operation.EQUAL) ? 0 : 1;
        }
        return correct / size;
    }
}
