package com.prodigy.core.scoring;

import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;

import java.util.List;

public interface ScoringStrategy {

    Point[] scoreWordDiff(List<Diff<Word>> diff);
}
