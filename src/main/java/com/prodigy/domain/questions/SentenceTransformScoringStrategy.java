package com.prodigy.domain.questions;


public interface SentenceTransformScoringStrategy {

    Score computeScore(SentenceTransform transform);
}
