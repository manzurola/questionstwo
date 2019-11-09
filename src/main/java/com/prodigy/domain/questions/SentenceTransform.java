package com.prodigy.domain.questions;

import java.util.List;

public class SentenceTransform {
    private final List<TransformationOperation> operations;

    public SentenceTransform(List<TransformationOperation> operations) {
        this.operations = operations;
    }

    public List<TransformationOperation> operations() {
        return operations;
    }

    @Override
    public String toString() {
        return "SentenceTransform{" +
                "operations=" + operations +
                '}';
    }
}
