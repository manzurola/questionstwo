package com.prodigy.domain.questions;

import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.nlp.SentenceFactory;

public class AnswerFactoryBuilderImpl implements AnswerFactoryBuilder {

    private final SentenceFactory sentenceFactory;
    private final SentenceDiffChecker diffChecker;
    private final SentenceDiffScoringStrategyFactory scoringStrategyFactory;
    private final SentenceDiffFeedbackProviderFactory feedbackProviderFactory;

    public AnswerFactoryBuilderImpl(SentenceFactory sentenceFactory,
                                    SentenceDiffChecker diffChecker,
                                    SentenceDiffScoringStrategyFactory scoringStrategyFactory,
                                    SentenceDiffFeedbackProviderFactory feedbackProviderFactory) {
        this.sentenceFactory = sentenceFactory;
        this.diffChecker = diffChecker;
        this.scoringStrategyFactory = scoringStrategyFactory;
        this.feedbackProviderFactory = feedbackProviderFactory;
    }

    @Override
    public AnswerFactory forQuestion(QuestionData question) {
        return new AnswerFactoryImpl(
                question, sentenceFactory,
                diffChecker,
                scoringStrategyFactory.create(),
                feedbackProviderFactory.create()
        );
    }
}
