package com.prodigy.grammar;

public interface SentenceDiffCheck {
    SentenceDiff diffSourceAndTarget(Sentence source, Sentence target);
}
