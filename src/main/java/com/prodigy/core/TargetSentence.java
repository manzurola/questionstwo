package com.prodigy.core;

public class TargetSentence {

    private final Sentence target;
    private final Sentence source;

    public TargetSentence(Sentence target, Sentence source) {
        this.target = target;
        this.source = source;
    }

    public Sentence target() {
        return target;
    }

    public Sentence source() {
        return source;
    }
}
