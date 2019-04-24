package com.prodigy.nlp.diff;

import java.util.List;

public interface SentenceDiff {

    String getSource();

    String getTarget();

    List<TextDiff> getTextDiffs();

    double distance();

}
