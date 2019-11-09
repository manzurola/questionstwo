package com.prodigy.recommend;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.diff.Operation;
import com.prodigy.domain.nlp.POS;
import com.prodigy.domain.nlp.Word;
import com.prodigy.domain.diff.SentenceDiff;

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
