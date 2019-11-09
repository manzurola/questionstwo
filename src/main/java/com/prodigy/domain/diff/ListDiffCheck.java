package com.prodigy.domain.diff;

import java.util.List;

public interface ListDiffCheck {
    <T> List<Diff<T>> checkDiff(List<T> source, List<T> target);

    <T> List<Diff<T>> checkDiff(List<T> source, List<T> target, HashingStrategy<T> hashingStrategy);
}
