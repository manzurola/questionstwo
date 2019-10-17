package com.prodigy.diff;

import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.Word;

import java.util.*;

public class SentenceDiffCheckerImpl implements SentenceDiffChecker {

    private final ListDiffChecker diffChecker;

    public SentenceDiffCheckerImpl(ListDiffChecker diffChecker) {
        this.diffChecker = diffChecker;
    }

    @Override
    public SentenceDiff diffSourceAndTarget(Sentence source, Sentence target) {
        List<Diff<Word>> words = diffChecker.checkDiff(source.words(), target.words(), word -> word.value().hashCode());
        return new SentenceDiff(words);
    }
}
