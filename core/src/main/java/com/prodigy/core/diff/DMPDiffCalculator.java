package com.prodigy.core.diff;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.*;

import static org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Operation.EQUAL;

public class DMPDiffCalculator implements DiffCalculator {

    private final DiffMatchPatch dmp;
    private boolean ignoreCase;

    public DMPDiffCalculator() {
        this.dmp = new DiffMatchPatch();
    }

    public DMPDiffCalculator(DiffMatchPatch dmp) {
        this.dmp = dmp;
    }


    @Override
    public <T> List<Diff<T>> getDiff(List<T> source, List<T> target) {
        Map<T, String> wordToChar = new HashMap<>();
        Map<String, T> charToWord = new HashMap<>();

        StringBuilder expectedChars = new StringBuilder();
        StringBuilder actualChars = new StringBuilder();

        // convert words to chars for word diff

        char c = 0;
        for (T word : target) {
            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, String.valueOf(c));
                charToWord.put(String.valueOf(c), word);
                c++; // 255 is limit!
            }
            String key = wordToChar.get(word);
            expectedChars.append(key);
        }

        for (T word : source) {
            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, String.valueOf(c));
                charToWord.put(String.valueOf(c), word);
                c++;
            }
            String key = wordToChar.get(word);
            actualChars.append(key);
        }

        // do diff
        LinkedList<DiffMatchPatch.Diff> diffs = doDiff(actualChars.toString(), expectedChars.toString());

        // expand diffs of more than one char to multiple single char diffs
        LinkedList<DiffMatchPatch.Diff> expanded = new LinkedList<>();
        for (DiffMatchPatch.Diff diff : diffs) {
            if (diff.text.length() > 1) {
                char[] chars = diff.text.toCharArray();
                for (char aChar : chars) {
                    expanded.add(new DiffMatchPatch.Diff(diff.operation, String.valueOf(aChar)));
                }
            } else {
                expanded.add(diff);
            }
        }

        return toTextDiffs(expanded, source, target);
    }

    private <T> List<Diff<T>> toTextDiffs(LinkedList<DiffMatchPatch.Diff> diffs, List<T> source, List<T> target) {
        LinkedList<T> sourceQueue = new LinkedList<>(source);
        LinkedList<T> targetQueue = new LinkedList<>(target);
        List<Diff<T>> result = new ArrayList<>();
        for (DiffMatchPatch.Diff diff : diffs) {
            switch (diff.operation) {
                case EQUAL:
                case DELETE:
                    result.add(new Diff<>(getOperation(diff.operation), sourceQueue.pop()));
                    targetQueue.pop();
                    break;
                case INSERT:
                    result.add(new Diff<>(getOperation(diff.operation), targetQueue.pop()));
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private Diff.Operation getOperation(DiffMatchPatch.Operation source) {
        switch (source) {
            case EQUAL:
                return Diff.Operation.EQUAL;
            case DELETE:
                return Diff.Operation.DELETE;
            case INSERT:
                return Diff.Operation.INSERT;
            default:
                throw new RuntimeException("Invalid operation mapping " + source);
        }
    }

    private LinkedList<DiffMatchPatch.Diff> doDiff(String actual, String expected) {
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(actual, expected);
        dmp.diffCleanupMerge(diffs);
        return diffs;
    }

}
