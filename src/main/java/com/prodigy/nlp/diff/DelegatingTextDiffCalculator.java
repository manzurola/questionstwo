package com.prodigy.nlp.diff;

import java.util.List;

public class DelegatingTextDiffCalculator implements TextDiffCalculator {
    @Override
    public List<TextDiff> diff(String origin, String target) {
        return null;
    }

    @Override
    public List<TextDiff> diff(List<String> origin, List<String> target) {
        return null;
    }

    @Override
    public double distance(List<TextDiff> diffs) {
        return 0;
    }
}
