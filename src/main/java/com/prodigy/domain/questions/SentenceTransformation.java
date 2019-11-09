package com.prodigy.domain.questions;

import java.util.List;

public class SentenceTransformation {
    private final List<TransformationOperation> operations;

    public SentenceTransformation(List<TransformationOperation> operations) {
        this.operations = operations;
    }

    public List<TransformationOperation> operations() {
        return operations;
    }
}
