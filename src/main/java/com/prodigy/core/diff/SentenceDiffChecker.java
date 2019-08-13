package com.prodigy.core.diff;

import com.prodigy.core.Word;

import java.util.*;

public class SentenceDiffChecker {

    private final DMPDiffCalculator diffCalculator;

    public SentenceDiffChecker(DMPDiffCalculator diffCalculator) {
        this.diffCalculator = diffCalculator;
    }

    public List<Diff<Word>> checkDiff(List<Word> source, List<Word> target) {
        return diffCalculator.getDiff(source, target, object -> object.value().hashCode());
    }
}
