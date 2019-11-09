package com.prodigy.domain.questions;


public class DummySentenceTransformScoringStrategy implements SentenceTransformScoringStrategy {

    @Override
    public Score computeScore(SentenceTransform transform) {
        return new Score(100);
    }
}
