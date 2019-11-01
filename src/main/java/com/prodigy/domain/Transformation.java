package com.prodigy.domain;

import com.prodigy.grammar.Sentence;

public class Transformation {

    private final Sentence from;
    private final Sentence to;

    public Transformation(Sentence from, Sentence to) {
        this.from = from;
        this.to = to;
    }

    public Sentence from() {
        return from;
    }

    public Sentence to() {
        return to;
    }
}
