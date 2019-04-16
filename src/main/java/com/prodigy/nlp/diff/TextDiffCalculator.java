package com.prodigy.nlp.diff;

import java.util.List;

public interface TextDiffCalculator {

    List<TextDiff> diff(String origin, String target);

    List<TextDiff> diff(List<String> origin, List<String> target);
}
