package com.prodigy.nlp.diff;

import java.util.List;

public interface TextEquality {

    boolean isEqual(List<String> source, List<String> target);
}
