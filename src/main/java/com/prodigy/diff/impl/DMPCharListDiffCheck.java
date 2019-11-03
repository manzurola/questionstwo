package com.prodigy.diff.impl;

import com.prodigy.diff.Diff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class DMPCharListDiffCheck {

    private final DMPWrapper dmp = new DMPWrapper();

    public final List<Diff<Character>> diffChars(List<Character> source, List<Character> target) {
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
