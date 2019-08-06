package com.prodigy.core.scoring;

import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;

import java.util.List;

public class SimpleScoringStrategy implements ScoringStrategy {
    @Override
    public Score scoreWordDiff(List<Diff<Word>> diff) {
        double[] points = new double[diff.size()];

    }
}
