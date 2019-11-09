package com.prodigy.domain.questions;

import com.prodigy.domain.Id;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.SentenceFactory;

public class AnswerFactoryImpl implements AnswerFactory {

    private final QuestionData question;
    private final SentenceFactory sentenceFactory;
    private final SentenceTransformation transformation;
    private final SentenceTransformScoringStrategy scoringStrategy;

    public AnswerFactoryImpl(QuestionData question,
                             SentenceFactory sentenceFactory,
                             SentenceTransformation transformation,
                             SentenceTransformScoringStrategy scoringStrategy) {
        this.question = question;
        this.sentenceFactory = sentenceFactory;
        this.transformation = transformation;
        this.scoringStrategy = scoringStrategy;
    }

    @Override
    public Answer create(String value) {
        Sentence source = sentenceFactory.fromString(value);
        Sentence target = sentenceFactory.fromString(question.solution());
        SentenceTransform transform = transformation.transform(source, target);
        Score score = scoringStrategy.computeScore(transform);          // experimental
        return new Answer(Id.next(), question.id(), source, target, transform, score);
    }

}
