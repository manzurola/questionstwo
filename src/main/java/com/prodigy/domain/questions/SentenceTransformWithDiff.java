package com.prodigy.domain.questions;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.nlp.Word;

import java.util.List;

public class SentenceTransformWithDiff extends SentenceTransform {
    private final List<Diff<Word>> diff;
    public SentenceTransformWithDiff(List<TransformationOperation> operations, List<Diff<Word>> diff) {
        super(operations);
        this.diff = diff;
    }

    public List<Diff<Word>> diff() {
        return diff;
    }

    @Override
    public String toString() {
        return "SentenceTransformWithDiff{" +
                "diff=" + diff +
                '}';
    }
}
