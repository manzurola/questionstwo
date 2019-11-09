package com.prodigy.domain.questions;

import com.prodigy.domain.nlp.SentenceFactory;

public class AnswerFactoryBuilderImpl implements AnswerFactoryBuilder {

    private final SentenceFactory sentenceFactory;
    private final SentenceTransformation sentenceTransformation;
    private final SentenceTransformScoringStrategyFactory scoringStrategyFactory;

    public AnswerFactoryBuilderImpl(SentenceFactory sentenceFactory,
                                    SentenceTransformation sentenceTransformation,
                                    SentenceTransformScoringStrategyFactory costFunctionFactoryFactory) {
        this.sentenceFactory = sentenceFactory;
        this.sentenceTransformation = sentenceTransformation;
        this.scoringStrategyFactory = costFunctionFactoryFactory;
    }

    @Override
    public AnswerFactory forQuestion(QuestionData question) {
        return new AnswerFactoryImpl(
                question,
                sentenceFactory,
                sentenceTransformation,
                scoringStrategyFactory.create()
        );
    }
}
