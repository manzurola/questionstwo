package com.prodigy.diff;

import com.prodigy.grammar.Word;

import java.util.List;

public class SentenceDiff {

    private final List<Diff<Word>> words;

    public SentenceDiff(List<Diff<Word>> words) {
        this.words = words;
    }

    public List<Diff<Word>> words() {
        return words;
    }

}
