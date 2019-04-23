package com.prodigy.nlp.diff;

public interface SentenceDiffCheck<T extends SentenceDiff> {

    T check(String source, String target);


}
