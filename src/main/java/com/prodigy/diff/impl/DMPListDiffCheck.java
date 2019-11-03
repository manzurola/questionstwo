package com.prodigy.diff.impl;

import com.prodigy.diff.*;

import java.util.*;
import java.util.stream.Collectors;

public final class DMPListDiffCheck implements ListDiffCheck {

    private final DMPCharListDiffCheck charListDiff = new DMPCharListDiffCheck();

    @Override
    public final <T> List<Diff<T>> checkDiff(List<T> source, List<T> target) {
        return checkDiff(source, target, Objects::hashCode);
    }

    @Override
    public final <T> List<Diff<T>> checkDiff(List<T> source, List<T> target, HashingStrategy<T> hashingStrategy) {
        List<Character> sourceChars = toCharList(source, hashingStrategy);
        List<Character> targetChars = toCharList(target, hashingStrategy);
        List<Diff<Character>> diffs = charListDiff.diffChars(sourceChars, targetChars);
        return resolveItems(diffs, source, target);
    }

    private <T> List<Character> toCharList(List<T> items, HashingStrategy<T> hashingStrategy) {
        return items.stream()
                .map(t -> hash(t, hashingStrategy))
                .collect(Collectors.toList());
    }

    private <T> char hash(T item, HashingStrategy<T> hashingStrategy) {
        //TODO mod char may not belong here (responsibility wise)
        return (char) (hashingStrategy.hashCode(item) % Character.MAX_VALUE);
    }

    private <T> List<Diff<T>> resolveItems(List<Diff<Character>> diff, List<T> source, List<T> target) {
        LinkedList<T> sourceQueue = new LinkedList<>(source);
        LinkedList<T> targetQueue = new LinkedList<>(target);
        List<Diff<T>> result = new ArrayList<>();
        for (Diff<Character> diffItem : diff) {
            Diff<T> resolvedDiff = resolveNextDiffItem(diffItem, sourceQueue, targetQueue);
            result.add(resolvedDiff);
        }
        return result;
    }

    private <T> Diff<T> resolveNextDiffItem(Diff<Character> nextDiff, LinkedList<T> sourceQueue, LinkedList<T> targetQueue) {
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
