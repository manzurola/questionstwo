package com.prodigy.diff;

import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.Word;

import java.util.*;

public class SentenceDiffCheckImpl implements SentenceDiffCheck {

    private final WordDiffCheck wordDiffChecker;

    public SentenceDiffCheckImpl(WordDiffCheck wordDiffChecker) {
        this.wordDiffChecker = wordDiffChecker;
    }

    @Override
    public SentenceDiff diffSourceAndTarget(Sentence source, Sentence target) {
        List<Diff<Word>> words = wordDiffChecker.diffSourceAndTarget(source.words(), target.words());
        return new SentenceDiff(words);
    }
}
