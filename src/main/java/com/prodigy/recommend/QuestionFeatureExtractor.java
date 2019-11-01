package com.prodigy.recommend;

import com.prodigy.grammar.SentenceDiff;
import com.prodigy.grammar.SentenceDiffCheck;
import com.prodigy.grammar.Sentence;
import com.prodigy.domain.Question;
import com.prodigy.grammar.SentenceFactory;

public class QuestionFeatureExtractor implements FeatureExtractor<Question> {

    private final SentenceFactory sentenceFactory;
    private final DiffPOSFeatureExtractor diffExtractor;
    private final SentenceDiffCheck sentenceDiffChecker;

    public QuestionFeatureExtractor(SentenceFactory sentenceFactory,
                                    DiffPOSFeatureExtractor diffExtractor,
                                    SentenceDiffCheck sentenceDiffChecker) {
        this.sentenceFactory = sentenceFactory;
        this.diffExtractor = diffExtractor;
        this.sentenceDiffChecker = sentenceDiffChecker;
    }

    @Override
    public Point extract(Question question) {
        return diffExtractor.extract(getDiffFromQuestion(question));
    }

    private SentenceDiff getDiffFromQuestion(Question question) {
        String source = question.getBody();
        String target = question.getAnswerKey().get(0);
        Sentence sourceSent = sentenceFactory.fromString(source);
        Sentence targetSent = sentenceFactory.fromString(target);
        return sentenceDiffChecker.diffSourceAndTarget(sourceSent, targetSent);
    }
}
