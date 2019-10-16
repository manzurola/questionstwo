package com.prodigy.diff;

import com.prodigy.grammar.Word;

import java.util.List;

public class WordValueDiffChecker implements WordDiffChecker {

    private final ListDiffCalculator diffCalculator;

    public WordValueDiffChecker(ListDiffCalculator diffCalculator) {
        this.diffCalculator = diffCalculator;
    }

    @Override
    public List<Diff<Word>> diff(List<Word> source, List<Word> target) {
        return diffCalculator.getDiff(source, target, word -> word.value().hashCode());
    }
}
