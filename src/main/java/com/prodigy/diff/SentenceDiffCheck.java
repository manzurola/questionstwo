package com.prodigy.diff;

import com.prodigy.grammar.Sentence;

public interface SentenceDiffCheck {
    SentenceDiff diffSourceAndTarget(Sentence source, Sentence target);
}
