package com.prodigy.diff;

import com.prodigy.grammar.Sentence;

public interface SentenceDiffChecker {

    SentenceDiff diffSourceAndTarget(Sentence source, Sentence target);
}
