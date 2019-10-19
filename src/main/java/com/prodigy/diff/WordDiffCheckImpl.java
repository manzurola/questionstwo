package com.prodigy.diff;

import com.prodigy.grammar.Word;

import java.util.List;

public class WordDiffCheckImpl implements WordDiffCheck {

    private final ListDiffCheck<Word> listDiffCheck = new ListDiffCheck<>(this::hashWord);

    @Override
    public List<Diff<Word>> diffSourceAndTarget(List<Word> source, List<Word> target) {
        return listDiffCheck.diffSourceAndTarget(source, target);
    }

    private int hashWord(Word word) {
        return word.value().hashCode();
    }
}
