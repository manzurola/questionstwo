package com.prodigy.core;

import com.prodigy.core.diff.Diff;

import java.util.List;

public class SentenceDiff {
    private final List<Diff<Word>> words;

    public SentenceDiff(List<Diff<Word>> words) {
        this.words = words;
    }

    public List<Diff<Word>> wordDiff() {
        return words;
    }

    @Override
    public String toString() {
        return "SentenceDiff{" +
                "words=" + words +
                '}';
    }
}
