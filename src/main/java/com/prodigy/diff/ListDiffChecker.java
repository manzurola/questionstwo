package com.prodigy.diff;

import java.util.*;
import java.util.stream.Collectors;

public final class ListDiffChecker {

    private final DMPUtils dmp;

    public ListDiffChecker() {
        this.dmp = new DMPUtils();
    }

    public ListDiffChecker(DMPUtils dmp) {
        this.dmp = dmp;
    }

    public final <T> List<Diff<T>> checkDiff(List<T> source, List<T> target) {
        return checkDiff(source, target, new CharHashMap<>());
    }

    public final <T> List<Diff<T>> checkDiff(List<T> source, List<T> target, HashingStrategy<T> hashingStrategy) {
        return checkDiff(source, target, new CharHashMap<>(hashingStrategy));
    }

    private <T> List<Diff<T>> checkDiff(List<T> source, List<T> target, CharHashMap<T> itemToCharMap) {
        List<Character> sourceAsChars = itemToCharMap.hashList(source);
        List<Character> targetAsChars = itemToCharMap.hashList(target);
        List<Diff<Character>> charDiff = dmp.checkDiff(sourceAsChars, targetAsChars);
        return revertToItemDiff(charDiff, source, target);
    }

    private <T> List<Diff<T>> revertToItemDiff(List<Diff<Character>> diff, List<T> source, List<T> target) {
        LinkedList<T> sourceQueue = new LinkedList<>(source);
        LinkedList<T> targetQueue = new LinkedList<>(target);
        List<Diff<T>> result = new ArrayList<>();
        for (Diff<Character> diffItem : diff) {
            switch (diffItem.operation()) {
                case EQUAL: // when equal, always take the target element
                    result.add(new Diff<>(Diff.Operation.EQUAL, targetQueue.remove()));
                    sourceQueue.remove();
                    break;
                case INSERT:
                    result.add(new Diff<>(Diff.Operation.INSERT, targetQueue.remove()));
                    break;
                case DELETE:
                    result.add(new Diff<>(Diff.Operation.DELETE, sourceQueue.remove()));
                    break;
                default:
                    throw new RuntimeException("Uknown Operation " + diffItem.operation());
            }
        }
        return result;
    }

    static class CharHashMap<T> {

        private static final int MAX_KEY_VALUE = Character.MAX_VALUE;
        private final HashingStrategy<T> itemHashingStrategy;

        CharHashMap(HashingStrategy<T> itemHashingStrategy) {
            this.itemHashingStrategy = itemHashingStrategy;
        }

        CharHashMap() {
            this.itemHashingStrategy = Object::hashCode;
        }

        List<Character> hashList(List<T> items) {
            return items.stream()
                    .map(this::hash)
                    .collect(Collectors.toList());
        }

        private char hash(T item) {
            int intHashCode = itemHashingStrategy.hashCode(item);
            return (char) (intHashCode % MAX_KEY_VALUE);
        }

    }
}
