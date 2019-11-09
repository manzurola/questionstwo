package com.prodigy.domain.diff;

import com.prodigy.domain.nlp.Sentence;

public interface SentenceDiffChecker {
    SentenceDiff diffSourceAndTarget(Sentence source, Sentence target);
}
