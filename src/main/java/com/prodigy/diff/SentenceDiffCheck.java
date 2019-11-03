package com.prodigy.diff;

import com.prodigy.nlp.Sentence;

public interface SentenceDiffCheck {
    SentenceDiff diffSourceAndTarget(Sentence source, Sentence target);
}
