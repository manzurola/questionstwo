package com.prodigy.engine.diff;

import java.util.List;

public interface DiffCalculator {

    <T> List<Diff<T>> getDiff(List<T> source, List<T> target);

}
