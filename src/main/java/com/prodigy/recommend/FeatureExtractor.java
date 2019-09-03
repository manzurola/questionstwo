package com.prodigy.recommend;

public interface FeatureExtractor<T> {

    Point extract(T data);
}
