package com.prodigy.domain.diff.impl;

import com.prodigy.domain.diff.*;
import com.prodigy.domain.nlp.Word;

import java.util.List;

public class DefaultWordDiffCheck implements WordDiffCheck {

    private final ListDiffCheck listDiffCheck;

    public DefaultWordDiffCheck(ListDiffCheck listDiffCheck) {
        this.listDiffCheck = listDiffCheck;
    }

    @Override
    public WordDiff diffWords(List<Word> source, List<Word> target) {
        List<Diff<Word>> words = listDiffCheck.checkDiff(source, target, this::hashWord);
        return new WordDiff(words);
    }

    private int hashWord(Word word) {
        return word.value().hashCode();
    }

}
