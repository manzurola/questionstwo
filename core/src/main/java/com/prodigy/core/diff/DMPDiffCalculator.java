package com.prodigy.core.diff;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.*;

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

        CharStringMapper<T> charStringMapper = new CharStringMapper<>();

        // Convert words to chars for text diff
        String sourceAsChars = charStringMapper.mapAll(source);
        String targetAsChars = charStringMapper.mapAll(target);

        // Do diff
        LinkedList<DiffMatchPatch.Diff> diffs = doDiff(sourceAsChars, targetAsChars);

        // The diff algorithm may return consecutive chars as a single diff.
        // We expand such diffs to multiple single char ones
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

        return resolveDiffs(expanded, charStringMapper);
    }

    private LinkedList<DiffMatchPatch.Diff> doDiff(String actual, String expected) {
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(actual, expected);
        dmp.diffCleanupMerge(diffs);
        return diffs;
    }

    private <T> List<Diff<T>> resolveDiffs(LinkedList<DiffMatchPatch.Diff> diffs, CharStringMapper<T> mapper) {
        List<Diff<T>> result = new ArrayList<>();
        for (DiffMatchPatch.Diff diff : diffs) {
            result.add(new Diff<>(translateOperation(diff.operation), mapper.get(diff.text)));
        }
        return result;
    }

    private Diff.Operation translateOperation(DiffMatchPatch.Operation source) {
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

    private static class CharStringMapper<T> {
        private static final char MAX_VALUES = Character.MAX_VALUE;
        private char charCode = 0;
        private Map<T, String> elemToChar = new HashMap<>();
        private Map<String, T> charToElem = new HashMap<>();
        private boolean maxed;

        String mapAll(List<T> elements) {

            StringBuilder buffer = new StringBuilder();

            for (T elem : elements) {
                String key = map(elem);
                buffer.append(key);
            }

            return buffer.toString();
        }


        String map(T element) {
            if (maxed) {
                throw new RuntimeException(String.format("The maximal number of unique values to diff (%d) was reached", MAX_VALUES));
            }
            if (!elemToChar.containsKey(element)) {
                String s = String.valueOf(charCode);
                elemToChar.put(element, s);
                charToElem.put(s, element);
                charCode++; // 255 is limit!

                if (charCode == MAX_VALUES) {
                    maxed = true;
                }
            }
            return elemToChar.get(element);
        }

        T get(String character) {
            return charToElem.get(character);
        }

    }

}
