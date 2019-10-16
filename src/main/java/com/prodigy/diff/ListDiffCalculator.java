package com.prodigy.diff;

import java.util.List;

public interface ListDiffCalculator {

    <T> List<Diff<T>> getDiff(List<T> source, List<T> target);

    <T> List<Diff<T>> getDiff(List<T> source, List<T> target, HashingStrategy<T> hashingStrategy);

}
