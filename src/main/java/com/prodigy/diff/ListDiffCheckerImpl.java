package com.prodigy.diff;

import java.util.*;

public class ListDiffCheckerImpl implements ListDiffChecker {

    @Override
    public <T> List<Diff<T>> checkDiff(List<T> source, List<T> target) {
        return checkDiff(source, target, new ItemToCharMapper<>());
    }

    @Override
    public <T> List<Diff<T>> checkDiff(List<T> source, List<T> target, HashingStrategy<T> hashingStrategy) {
        return checkDiff(source, target, new ItemToCharMapper<>(hashingStrategy));
    }

    private <T> List<Diff<T>> checkDiff(List<T> source, List<T> target, ItemToCharMapper<T> itemToCharMapper) {
        CharSequence sourceAsChars = itemToCharMapper.mapList(source);
        CharSequence targetAsChars = itemToCharMapper.mapList(target);
        List<Diff<Character>> charDiff = getTextDiff(sourceAsChars, targetAsChars);
        return resolveItemsFromChars(charDiff, source, target);
    }

    private List<Diff<Character>> getTextDiff(CharSequence source, CharSequence target) {
        DMP dmp = new DMP();
        return dmp.getTextDiff(source, target);
    }

    private <T> List<Diff<T>> resolveItemsFromChars(List<Diff<Character>> diff, List<T> source, List<T> target) {
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

}
