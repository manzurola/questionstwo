package com.prodigy.domain.questions;

import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.Word;

import java.io.StringWriter;

public class DeleteWord implements TransformationOperation {

    private final Sentence before;
    private final Sentence after;
    private final int index;
    private final Word deleted;

    public DeleteWord(Sentence before, int index) {
        this.before = before;
        this.after = before.deleteWordAt(index);
        this.index = index;
        this.deleted = before.wordAt(index);
    }

    @Override
    public String operation() {
        return "delete";
    }

    @Override
    public Sentence before() {
        return before;
    }

    @Override
    public Sentence after() {
        return after;
    }

    public Word deletedWord() {
        return deleted;
    }

    @Override
    public String toString() {
        String before = String.format("Before: [%s]\n", this.before.value());
        String operation = String.format("Operation: Delete word at [%d]. Deleted word is [%s]\n",index, deleted.value());
        String after = String.format("After: [%s]\n", this.after.value());
        return new StringWriter()
                .append(before)
                .append(operation)
                .append(after)
                .toString();
    }
}
