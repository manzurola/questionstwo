package com.prodigy.diff;

import com.prodigy.grammar.Sentence;

public interface SentenceDiffChecker {

    SentenceDiff diff(Sentence source, Sentence target);
}
