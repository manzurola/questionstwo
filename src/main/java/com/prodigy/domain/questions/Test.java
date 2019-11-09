package com.prodigy.domain.questions;

import com.prodigy.domain.diff.impl.DMPListDiffCheck;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.SentenceFactory;
import com.prodigy.domain.nlp.impl.CoreNLPSentenceFactory;

public class Test {

//    public static void main(String[] args) {
//        SentenceFactory sentenceParser = new CoreNLPSentenceFactory();
//        SentenceTransformation sentenceTransformation = new DiffBasedSentenceTransformation(new DMPListDiffCheck());
//        SentenceTransformScoringStrategyFactory costFunctionFactory = new DummySentenceTransformScoringStrategyFactory();
//        AnswerFactoryBuilder answerFactoryBuilder = new AnswerFactoryBuilderImpl(
//                sentenceParser,
//                sentenceTransformation,
//                costFunctionFactory
//        );
//
//        QuestionFactory questionFactory = new QuestionFactoryImpl(answerFactoryBuilder);
//
//        Question question = questionFactory.create("A dog is cute.", "put into the plural", "Dogs are cute.");
//        Answer answer = question.newAnswer("A dogs are cute");
//        System.out.println(answer);
//    }

    public static void main(String[] args) {
        SentenceTransformation sentenceTransformation = new DiffBasedSentenceTransformation(new DMPListDiffCheck());
        SentenceFactory sentenceFactory = new CoreNLPSentenceFactory();
        Sentence source = sentenceFactory.fromString("A dogs are cute");
        Sentence target = sentenceFactory.fromString("Dogs are cute.");
        SentenceTransform transform = sentenceTransformation.transform(source, target);
        for (TransformationOperation operation : transform.operations()) {
            System.out.println(operation);
        }

    }

}
