package com.prodigy.domain.questions;

import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.Word;

import java.io.StringWriter;

public class InsertWord implements TransformationOperation {

    private final Sentence before;
    private final Sentence after;
    private final int index;
    private final Word word;

    public InsertWord(Sentence before, int index, Word word) {
        this.before = before;
        this.after = index >= before.size() ? before.addWord(word.value()) : before.addWord(index, word.value());
        this.index = index;
        this.word = word;
    }

    @Override
    public String operation() {
        return "insert";
    }

    @Override
    public Sentence before() {
        return before;
    }

    @Override
    public Sentence after() {
        return after;
    }

    @Override
    public String toString() {
        String before = String.format("Before: [%s]\n", this.before.value());
        String operation = String.format("Operation: Insert [%s] at [%d]\n", word.value(), index);
        String after = String.format("After: [%s]\n", this.after.value());
        return new StringWriter()
                .append(before)
                .append(operation)
                .append(after)
                .toString();
    }
}
