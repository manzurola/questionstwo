package com.prodigy.nlp.diff;

import com.prodigy.nlp.Sentence;

import java.util.List;

public class GrammaticalSentenceDiff implements SentenceDiff {

    private final Sentence source;
    private final Sentence target;
    private final List<WordDiff> diff;
    private List<TextDiff> textDiffs;

    public GrammaticalSentenceDiff(Sentence source, Sentence target, List<WordDiff> diff, List<TextDiff> textDiffs) {
        this.source = source;
        this.target = target;
        this.diff = diff;
        this.textDiffs = textDiffs;
    }

    @Override
    public String getSource() {
        return source.getValue();
    }

    @Override
    public String getTarget() {
        return target.getValue();
    }

    @Override
    public List<TextDiff> getTextDiffs() {
        return textDiffs;
    }

    @Override
    public double distance() {
        return 0;
    }

    public Sentence getSourceSentence() {
        return source;
    }

    public Sentence getTargetSentence() {
        return target;
    }

    public List<WordDiff> getWordDiff() {
        return diff;
    }

}
