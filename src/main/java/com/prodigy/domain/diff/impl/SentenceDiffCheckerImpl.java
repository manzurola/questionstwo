package com.prodigy.domain.diff.impl;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.diff.ListDiffCheck;
import com.prodigy.domain.diff.SentenceDiff;
import com.prodigy.domain.diff.SentenceDiffChecker;
import com.prodigy.domain.nlp.*;

import java.util.*;

public class SentenceDiffCheckerImpl implements SentenceDiffChecker {

    private final ListDiffCheck listDiffCheck;

    public SentenceDiffCheckerImpl(ListDiffCheck listDiffCheck) {
        this.listDiffCheck = listDiffCheck;
    }

    @Override
    public SentenceDiff diffSourceAndTarget(Sentence source, Sentence target) {
        List<Diff<Word>> words = listDiffCheck.checkDiff(source.words(), target.words(), this::hashWord);
        return new SentenceDiff(source, target, words);
    }

    private int hashWord(Word word) {
        return word.value().hashCode();
    }
}
