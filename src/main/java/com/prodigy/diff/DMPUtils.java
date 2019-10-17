package com.prodigy.diff;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class DMPUtils {

    public List<Diff<Character>> checkDiff(List<Character> source, List<Character> target) {
        return splitByChars(checkDiff(
                source.stream().map(String::valueOf).collect(Collectors.joining()),
                target.stream().map(String::valueOf).collect(Collectors.joining())
        ));
    }

    public List<Diff<String>> checkDiff(String source, String target) {
        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(source, target, false);
        dmp.diffCleanupMerge(diffs);
        return translateDMPDiffs(diffs);
    }

    public List<Diff<Character>> splitByChars(List<Diff<String>> diff) {
        List<Diff<Character>> result = new ArrayList<>();
        for (Diff<String> aDiff : diff) {
            result.addAll(charSplit(aDiff));
        }
        return result;
    }

    private List<Diff<String>> translateDMPDiffs(List<DiffMatchPatch.Diff> dmpDiffs) {
        List<Diff<String>> translated = new ArrayList<>();
        for (DiffMatchPatch.Diff dmpDiff : dmpDiffs) {
            translated.add(translateDMPDiff(dmpDiff));
        }
        return translated;
    }

    private Diff<String> translateDMPDiff(DiffMatchPatch.Diff dmpDiff) {
        switch (dmpDiff.operation) {
            case EQUAL: // when equal, always take the target element
                return new Diff<>(Diff.Operation.EQUAL, dmpDiff.text);
            case INSERT:
                return new Diff<>(Diff.Operation.INSERT, dmpDiff.text);
            case DELETE:
                return new Diff<>(Diff.Operation.DELETE, dmpDiff.text);
            default:
                throw new RuntimeException("Uknown Operation " + dmpDiff.operation);
        }
    }

    private List<Diff<Character>> charSplit(Diff<String> diff) {
        List<Diff<Character>> result = new ArrayList<>();
        for (char c : diff.item().toCharArray()) {
            result.add(new Diff<>(diff.operation(), c));
        }
        return result;
    }
}
