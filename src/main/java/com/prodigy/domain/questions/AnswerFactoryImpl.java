package com.prodigy.domain.questions;

import com.prodigy.domain.Id;
import com.prodigy.domain.diff.SentenceDiff;
import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.SentenceFactory;

public class AnswerFactoryImpl implements AnswerFactory {

    private final QuestionData question;
    private final SentenceFactory sentenceFactory;
    private final SentenceDiffChecker diffCheck;
    private final SentenceDiffScoringStrategy scoringStrategy;
    private final SentenceDiffFeedbackProvider feedbackProvider;

    public AnswerFactoryImpl(QuestionData question,
                             SentenceFactory sentenceFactory,
                             SentenceDiffChecker diffChecker,
                             SentenceDiffScoringStrategy scoringStrategy,
                             SentenceDiffFeedbackProvider feedbackProvider) {
        this.question = question;
        this.sentenceFactory = sentenceFactory;
        this.diffCheck = diffChecker;
        this.scoringStrategy = scoringStrategy;
        this.feedbackProvider = feedbackProvider;
    }

    @Override
    public Answer create(String value) {
        Sentence valueSentence = sentenceFactory.fromString(value);
        Sentence solutionSentence = sentenceFactory.fromString(question.solution());
        SentenceDiff sentenceDiff = diffCheck.diffSourceAndTarget(valueSentence, solutionSentence);

        Score score = scoringStrategy.scoreDiff(sentenceDiff);          // experimental
        String feedback = feedbackProvider.getFeedback(sentenceDiff);   // experimental

        return new Answer(Id.next(), question.id(), valueSentence, sentenceDiff, score, feedback);
    }

}
