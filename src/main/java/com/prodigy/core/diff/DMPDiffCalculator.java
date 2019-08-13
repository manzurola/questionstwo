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
        return getDiff(source, target, new ObjectCharMap<>());
    }

    @Override
    public <T> List<Diff<T>> getDiff(List<T> source, List<T> target, HashingStrategy<T> hashingStrategy) {
        return getDiff(source, target, new ObjectCharMap<>(hashingStrategy));
    }

    private <T> List<Diff<T>> getDiff(List<T> source, List<T> target, ObjectCharMap<T> objectCharMap) {

        // Convert words to chars for text diff
        String sourceAsChars = objectCharMap.mapAll(source);
        String targetAsChars = objectCharMap.mapAll(target);

        // Do diff
        LinkedList<DiffMatchPatch.Diff> diffs = diff(sourceAsChars, targetAsChars);

        // The diff algorithm may return consecutive chars as a single diff.
        // We expand such diffs to multiple single char ones
        LinkedList<DiffMatchPatch.Diff> expanded = expand(diffs);

        return resolve(expanded, source, target);
    }

    private LinkedList<DiffMatchPatch.Diff> diff(String actual, String expected) {
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(actual, expected, false);
        dmp.diffCleanupMerge(diffs);
        return diffs;
    }

    private LinkedList<DiffMatchPatch.Diff> expand(LinkedList<DiffMatchPatch.Diff> diffs) {
        LinkedList<DiffMatchPatch.Diff> result = new LinkedList<>();
        for (DiffMatchPatch.Diff diff : diffs) {
            if (diff.text.length() > 1) {
                char[] chars = diff.text.toCharArray();
                for (char aChar : chars) {
                    result.add(new DiffMatchPatch.Diff(diff.operation, String.valueOf(aChar)));
                }
            } else {
                result.add(diff);
            }
        }

        return result;
    }

    private <T> List<Diff<T>> resolve(LinkedList<DiffMatchPatch.Diff> diff, List<T> source, List<T> target) {
        LinkedList<T> sourceQueue = new LinkedList<>(source);
        System.out.println(sourceQueue);
        LinkedList<T> targetQueue = new LinkedList<>(target);
        System.out.println(targetQueue);
        List<Diff<T>> result = new ArrayList<>();

        for (DiffMatchPatch.Diff diffItem : diff) {
            switch (diffItem.operation) {
                case EQUAL: // when equal, always take the target element
                    result.add(new Diff<>(translateOperation(DiffMatchPatch.Operation.EQUAL), targetQueue.remove()));
                    sourceQueue.remove();
                    break;
                case INSERT:
                    result.add(new Diff<>(translateOperation(DiffMatchPatch.Operation.INSERT), targetQueue.remove()));
                    break;
                case DELETE:
                    result.add(new Diff<>(translateOperation(DiffMatchPatch.Operation.DELETE), sourceQueue.remove()));
                    break;
            }
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
        private final Map<Integer, Character> hashToChar = new HashMap<>();
        private final HashingStrategy<T> hashingStrategy;

        public ObjectCharMap(HashingStrategy<T> hashingStrategy) {
            this.hashingStrategy = hashingStrategy;
        }

        public ObjectCharMap() {
            this.hashingStrategy = Object::hashCode;
        }

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
            int hashCode = hashingStrategy.hashCode(object);
            if (!hashToChar.containsKey(hashCode)) {
                hashToChar.put(hashCode, nextKey);
                nextKey++;

                if (nextKey == MAX_KEY_VALUE) {
                    maxed = true;
                }
            }
            return hashToChar.get(hashCode);
        }

    }
}
