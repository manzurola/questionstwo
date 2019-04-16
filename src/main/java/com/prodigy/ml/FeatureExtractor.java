package com.prodigy.ml;

public interface FeatureExtractor<T> {

    FeatureVector<T> extract(T data);
}
