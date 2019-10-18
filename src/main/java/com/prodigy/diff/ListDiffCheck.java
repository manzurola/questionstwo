package com.prodigy.diff;

import java.util.*;
import java.util.stream.Collectors;

public final class ListDiffCheck<T> {

    private final CharListDiffCheck charListDiff = new CharListDiffCheck();
    private final HashingStrategy<T> hashingStrategy;

    public ListDiffCheck() {
        this(Objects::hashCode);
    }

    public ListDiffCheck(HashingStrategy<T> hashingStrategy) {
        this.hashingStrategy = hashingStrategy;
    }

    public final List<Diff<T>> diffSourceAndTarget(List<T> source, List<T> target) {
        List<Character> sourceChars = toCharList(source);
        List<Character> targetChars = toCharList(target);
        List<Diff<Character>> diffs = charListDiff.diffSourceAndTarget(sourceChars, targetChars);
        return resolveItems(diffs, source, target);
    }

    private List<Character> toCharList(List<T> items) {
        return items.stream().map(this::hash).collect(Collectors.toList());
    }

    private char hash(T item) {
        return (char) (hashingStrategy.hashCode(item) % Character.MAX_VALUE);
    }

    private List<Diff<T>> resolveItems(List<Diff<Character>> diff, List<T> source, List<T> target) {
        LinkedList<T> sourceQueue = new LinkedList<>(source);
        LinkedList<T> targetQueue = new LinkedList<>(target);
        List<Diff<T>> result = new ArrayList<>();
        for (Diff<Character> diffItem : diff) {
            Diff<T> resolvedDiff = resolveNextDiffItem(diffItem, sourceQueue, targetQueue);
            result.add(resolvedDiff);
        }
        return result;
    }

    private Diff<T> resolveNextDiffItem(Diff<Character> nextDiff, LinkedList<T> sourceQueue, LinkedList<T> targetQueue) {
        switch (nextDiff.operation()) {
            case EQUAL: // when equal, always take the target element
                sourceQueue.remove();
                return new Diff<>(Operation.EQUAL, targetQueue.remove());
            case INSERT:
                return new Diff<>(Operation.INSERT, targetQueue.remove());
            case DELETE:
                return new Diff<>(Operation.DELETE, sourceQueue.remove());
            default:
                throw new RuntimeException("Uknown Operation " + nextDiff.operation());
        }
    }
}
