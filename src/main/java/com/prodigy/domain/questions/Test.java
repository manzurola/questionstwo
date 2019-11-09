package com.prodigy.domain.questions;

import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.diff.impl.DMPListDiffCheck;
import com.prodigy.domain.diff.impl.SentenceDiffCheckerImpl;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.SentenceFactory;
import com.prodigy.domain.nlp.impl.CoreNLPSentenceFactory;

public class Test {

//    public static void main(String[] args) {
//        CoreNLPSentenceFactory sentenceParser = new CoreNLPSentenceFactory();
//        SentenceDiffChecker diffChecker = new SentenceDiffCheckerImpl(new DMPListDiffCheck());
//        SentenceDiffScoringStrategyFactory scoringStrategyFactory = new DummySentenceDiffScoringStrategyFactory();
//        SentenceDiffFeedbackProviderFactory feedbackProviderFactory = new DummySentenceDiffFeedbackProviderFactory();
//        AnswerFactoryBuilder answerFactoryBuilder = new AnswerFactoryBuilderImpl(
//                sentenceParser,
//                diffChecker,
//                scoringStrategyFactory,
//                feedbackProviderFactory
//        );
//
//        QuestionFactory questionFactory = new QuestionFactoryImpl(answerFactoryBuilder);
//
//        Question question = questionFactory.create("A dog is cute.", "put into the plural", "Dogs are cute.");
//        Answer answer = question.newAnswer("A dogs are cute");
//
//    }

    public static void main(String[] args) {
        SentenceTransformer sentenceTransformer = new DiffBasedSentenceTransformer(new DMPListDiffCheck());
        SentenceFactory sentenceFactory = new CoreNLPSentenceFactory();
        Sentence source = sentenceFactory.fromString("A dogs cute are.");
        Sentence target = sentenceFactory.fromString("Dogs are cute.");
        SentenceTransformation transform = sentenceTransformer.transform(source, target);
        for (TransformationOperation operation : transform.operations()) {
            System.out.println(operation);
        }

    }

}
