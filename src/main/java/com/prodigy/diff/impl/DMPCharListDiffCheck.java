package com.prodigy.diff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class CharListDiffCheck {

    private final DMPWrapper dmp = new DMPWrapper();

    final List<Diff<Character>> diffSourceAndTarget(List<Character> source, List<Character> target) {
        String sourceConcatenated = source.stream().map(String::valueOf).collect(Collectors.joining());
        String targetConcatenated = target.stream().map(String::valueOf).collect(Collectors.joining());
        List<Diff<String>> diffs = dmp.checkDiff(sourceConcatenated, targetConcatenated);
        return splitByChars(diffs);
    }

    private List<Diff<Character>> splitByChars(List<Diff<String>> diff) {
        List<Diff<Character>> result = new ArrayList<>();
        for (Diff<String> aDiff : diff) {
            result.addAll(getCharDiff(aDiff));
        }
        return result;
    }

    private List<Diff<Character>> getCharDiff(Diff<String> diff) {
        List<Diff<Character>> result = new ArrayList<>();
        for (char c : diff.item().toCharArray()) {
            result.add(new Diff<>(diff.operation(), c));
        }
        return result;
    }
}
