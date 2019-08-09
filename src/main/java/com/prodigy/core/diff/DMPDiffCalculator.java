package com.prodigy.core.diff;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.*;

public class DMPDiffCalculator implements DiffCalculator {

    private final DiffMatchPatch dmp;

    public DMPDiffCalculator() {
        this.dmp = new DiffMatchPatch();
    }

    public DMPDiffCalculator(DiffMatchPatch dmp) {
        this.dmp = dmp;
    }

    @Override
    public <T> List<Diff<T>> getDiff(List<T> source, List<T> target) {
        ObjectCharMap<T> objectCharMap = new ObjectCharMap<>();

        // Convert words to chars for text diff
        String sourceAsChars = objectCharMap.mapAll(source);
        String targetAsChars = objectCharMap.mapAll(target);

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

        return resolveDiffs(expanded, objectCharMap);
    }

    private LinkedList<DiffMatchPatch.Diff> doDiff(String actual, String expected) {
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(actual, expected, false);
        dmp.diffCleanupMerge(diffs);
        return diffs;
    }

    private <T> List<Diff<T>> resolveDiffs(LinkedList<DiffMatchPatch.Diff> diffs, ObjectCharMap<T> mapper) {
        List<Diff<T>> result = new ArrayList<>();
        for (DiffMatchPatch.Diff diff : diffs) {
            result.add(new Diff<>(translateOperation(diff.operation), mapper.get(diff.text.charAt(0))));
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

    private static class ObjectCharMap<T> {

        private static final int MAX_KEY_VALUE = Character.MAX_VALUE;
        private boolean maxed;
        private char nextKey = 0;
        private final Map<T, Character> objectToChar = new HashMap<>();
        private final Map<Character, T> charToObject = new HashMap<>();

        String mapAll(List<T> objects) {
            StringBuilder buffer = new StringBuilder();

            for (T elem : objects) {
                char key = map(elem);
                buffer.append(key);
            }

            return buffer.toString();
        }

        char map(T object) {
            if (maxed) {
                throw new RuntimeException(String.format("The maximal number of unique objects to map (%d) was reached", MAX_KEY_VALUE));
            }
            if (!objectToChar.containsKey(object)) {
                objectToChar.put(object, nextKey);
                charToObject.put(nextKey, object);
                nextKey++;

                if (nextKey == MAX_KEY_VALUE) {
                    maxed = true;
                }
            }
            return objectToChar.get(object);
        }

        T get(char key) {
            return charToObject.get(key);
        }

    }

}
