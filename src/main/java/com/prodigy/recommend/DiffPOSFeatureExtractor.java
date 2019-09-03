package com.prodigy.recommend;

import com.prodigy.core.SentenceDiff;
import com.prodigy.core.Word;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.nlp.POS;
import com.prodigy.recommend.Point;

import java.util.Arrays;

public class DiffPOSFeatureExtractor {

    public Point extract(SentenceDiff sentenceDiff) {
        double[] point = new double[POS.values().length];
        Arrays.fill(point, 0);

        for (Diff<Word> wordDiff : sentenceDiff.wordDiff()) {
            if (!Diff.Operation.EQUAL.equals(wordDiff.operation())) {
                POS pos = wordDiff.object().posTag();
                if (pos != null) {
                    point[pos.ordinal()] = 1;
                }
            }
        }

        return new Point(point);
    }


}
