package com.prodigy.recommend;

import com.prodigy.diff.Diff;
import com.prodigy.diff.Operation;
import com.prodigy.grammar.POS;
import com.prodigy.grammar.Word;
import com.prodigy.grammar.SentenceDiff;

import java.util.Arrays;

public class DiffPOSFeatureExtractor {

    public Point extract(SentenceDiff sentenceDiff) {
        double[] point = new double[POS.values().length];
        Arrays.fill(point, 0);

        for (Diff<Word> wordDiff : sentenceDiff.words()) {
            if (!Operation.EQUAL.equals(wordDiff.operation())) {
                POS pos = wordDiff.item().posTag();
                if (pos != null) {
                    point[pos.ordinal()] = 1;
                }
            }
        }

        return new Point(point);
    }


}
