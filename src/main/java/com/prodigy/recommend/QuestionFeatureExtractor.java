package com.prodigy.recommend;

import com.prodigy.core.SentenceDiff;
import com.prodigy.core.diff.SentenceDiffChecker;
import com.prodigy.domain.questions.Question;
import com.prodigy.core.Sentence;
import com.prodigy.core.SentenceFactory;
import com.prodigy.core.diff.DMPDiffCalculator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        SentenceDiffChecker diffChecker = new SentenceDiffChecker(new DMPDiffCalculator());
        return diffChecker.checkDiff(sourceSent, targetSent);
    }
}
