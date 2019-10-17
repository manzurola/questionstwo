package com.prodigy.recommend;

import com.prodigy.diff.SentenceDiff;
import com.prodigy.diff.SentenceDiffCheckerImpl;
import com.prodigy.grammar.Sentence;
import com.prodigy.questions.Question;
import com.prodigy.grammar.SentenceFactory;
import com.prodigy.diff.ListDiffChecker;

import java.util.List;

public class QuestionFeatureExtractor implements FeatureExtractor<Question> {

    private final SentenceFactory sentenceFactory;
    private final DiffPOSFeatureExtractor diffExtractor;

    public QuestionFeatureExtractor(SentenceFactory sentenceFactory, DiffPOSFeatureExtractor diffExtractor) {
        this.sentenceFactory = sentenceFactory;
        this.diffExtractor = diffExtractor;
    }

    public QuestionFeatureExtractor(List<Question> dataset, SentenceFactory sentenceFactory,DiffPOSFeatureExtractor diffExtractor) {
        this.sentenceFactory = sentenceFactory;
        this.diffExtractor = diffExtractor;
    }

    @Override
    public Point extract(Question question) {
        return diffExtractor.extract(getDiffFromQuestion(question));
    }

    private SentenceDiff getDiffFromQuestion(Question question) {
        String source = question.getBody();
        String target = question.getAnswerKey().get(0);
        Sentence sourceSent = sentenceFactory.getSentence(source);
        Sentence targetSent = sentenceFactory.getSentence(target);
        SentenceDiffCheckerImpl diffChecker = new SentenceDiffCheckerImpl(new ListDiffChecker());
        return diffChecker.diffSourceAndTarget(sourceSent, targetSent);
    }
}
