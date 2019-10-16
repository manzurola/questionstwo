package com.prodigy.diff;

import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.Word;

import java.util.*;

public class SentenceDiffCheckerImpl implements SentenceDiffChecker {

    private final WordDiffChecker wordDiffChecker;

    public SentenceDiffCheckerImpl(WordDiffChecker wordDiffChecker) {
        this.wordDiffChecker = wordDiffChecker;
    }

    @Override
    public SentenceDiff diff(Sentence source, Sentence target) {
        List<Diff<Word>> words = wordDiffChecker.diff(source.words(), target.words());
        return new SentenceDiff(words);
    }
}
