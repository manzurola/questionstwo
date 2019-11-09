package com.prodigy.domain.questions;

import com.prodigy.domain.diff.SentenceDiff;

public interface SentenceDiffScoringStrategy {

    Score scoreDiff(SentenceDiff diff);
}
