package com.prodigy.nlp.diff;

import com.prodigy.nlp.Sentence;

public interface SentenceDiffCheck {

    SentenceDiff check(Sentence source, Sentence target);
}
