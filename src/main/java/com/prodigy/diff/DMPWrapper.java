package com.prodigy.diff;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class DMPWrapper {

    private final DiffMatchPatch dmp = new DiffMatchPatch();

    List<Diff<Character>> getTextDiff(CharSequence source, CharSequence target) {
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(source.toString(), target.toString(), false);
        dmp.diffCleanupMerge(diffs);
        List<Diff<String>> translated = translateFromDMP(diffs);
        return toChars(translated);
    }

    private List<Diff<String>> translateFromDMP(List<DiffMatchPatch.Diff> dmpDiffs) {
        List<Diff<String>> translated = new ArrayList<>();
        for (DiffMatchPatch.Diff dmpDiff : dmpDiffs) {
            translated.add(translateFromDMP(dmpDiff));
        }
        return translated;
    }

    private Diff<String> translateFromDMP(DiffMatchPatch.Diff dmpDiff) {
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

    private List<Diff<Character>> toChars(List<Diff<String>> diffs) {
        List<Diff<Character>> result = new ArrayList<>();
        for (Diff<String> diff : diffs) {
            result.addAll(charSplit(diff));
        }
        return result;
    }

    private List<Diff<Character>> charSplit(Diff<String> diff) {
        List<Diff<Character>> result = new ArrayList<>();
        for (char c : diff.item().toCharArray()) {
            result.add(new Diff<>(diff.operation(), c));
        }
        return result;
    }
}
