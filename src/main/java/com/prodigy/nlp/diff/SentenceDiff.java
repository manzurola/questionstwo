package com.prodigy.nlp.diff;

import com.prodigy.nlp.Sentence;

import java.util.List;

public class SentenceDiff {

    private final Sentence source;
    private final Sentence target;
    private final List<WordDiff> diff;

    public SentenceDiff(Sentence source, Sentence target, List<WordDiff> diff) {
        this.source = source;
        this.target = target;
        this.diff = diff;
    }

    public Sentence getSource() {
        return source;
    }

    public Sentence getTarget() {
        return target;
    }

    public List<WordDiff> getDiff() {
        return diff;
    }


}
