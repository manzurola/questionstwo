package com.prodigy.nlp.diff;

import java.util.List;

public class SimpleSentenceDiff implements SentenceDiff {

    private final String source;
    private final String target;
    private final List<TextDiff> textDiffs;
    private final double distance;

    public SimpleSentenceDiff(String source, String target, List<TextDiff> textDiffs, double distance) {
        this.source = source;
        this.target = target;
        this.textDiffs = textDiffs;
        this.distance = distance;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public List<TextDiff> getTextDiffs() {
        return textDiffs;
    }

    @Override
    public double distance() {
        return distance;
    }

}
