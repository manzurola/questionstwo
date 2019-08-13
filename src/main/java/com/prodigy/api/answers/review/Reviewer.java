package com.prodigy.api.answers.review;

import com.prodigy.api.questions.Question;
import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.SentenceDiffChecker;
import com.prodigy.core.nlp.SentenceTokenizer;

import java.util.List;

public class Reviewer {

    private final SentenceTokenizer tokenizer;
    private final SentenceDiffChecker diffChecker;

    public Reviewer(SentenceTokenizer tokenizer, SentenceDiffChecker diffChecker) {
        this.tokenizer = tokenizer;
        this.diffChecker = diffChecker;
    }

    public Review reviewAnswer(String answer, Question question) {

        // tokenize and tag words
        List<Word> parsedAnswer = tokenizer.tokenize(answer);
        List<Word> parsedTarget = tokenizer.tokenize(question.getAnswerKey().get(0));
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

        return Review.newBuilder()
                .diff(diff)
                .isCorrect(isCorrect)
                .build();
    }

}
