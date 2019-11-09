package com.prodigy.domain.questions;

import com.prodigy.domain.diff.SentenceDiff;

public class DummySentenceDiffScoringStrategy implements SentenceDiffScoringStrategy {
    @Override
    public Score scoreDiff(SentenceDiff diff) {
        return new Score(100);
    }
}
