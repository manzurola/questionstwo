package com.prodigy.nlp.impl;

import com.prodigy.diff.Diff;
import com.prodigy.diff.ListDiffCheck;
import com.prodigy.diff.SentenceDiff;
import com.prodigy.diff.SentenceDiffCheck;
import com.prodigy.nlp.*;

import java.util.*;

public class SentenceDiffCheckImpl implements SentenceDiffCheck {

    private final ListDiffCheck listDiffCheck;

    public SentenceDiffCheckImpl(ListDiffCheck listDiffCheck) {
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