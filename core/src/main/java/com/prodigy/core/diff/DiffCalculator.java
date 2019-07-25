package com.prodigy.core.diff;

import java.util.List;

public interface DiffCalculator {

    <T> List<Diff<T>> getDiff(List<T> source, List<T> target);
}
