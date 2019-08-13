package com.prodigy.core;

import com.prodigy.core.diff.Diff;

import java.util.List;

public class SentenceDiff {

    private final String source;
    private final String target;
    private final List<Diff<WordWrapper>> diff;

    public SentenceDiff(String source, String target, List<Diff<WordWrapper>> diff) {
        this.source = source;
        this.target = target;
        this.diff = diff;
    }

    @Override
    public String toString() {
        return "SentenceDiff{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                ", diff=" + prettyPrintDiff() +
                '}';
    }

    private String prettyPrintDiff() {
        StringBuilder builder = new StringBuilder();
        for (Diff<WordWrapper> wordWrapperDiff : diff) {
            String op = wordWrapperDiff.operation().equals(Diff.Operation.DELETE) ? "(-)" : (wordWrapperDiff.operation().equals(Diff.Operation.INSERT) ? "(+)" : "");
            builder.append(String.format("%s%s ", op, wordWrapperDiff.object().getWord().word()));
        }
        return builder.toString();
    }
}
