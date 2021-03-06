package com.prodigy.recommend.clustering;

import com.prodigy.recommend.Point;

public class Clusterable<T> {

    private final Point vector;
    private final T data;

    public Clusterable(Point vector, T data) {
        this.vector = vector;
        this.data = data;
    }

    public double[] point() {
        return vector.getPoint();
    }

    public T data() {
        return data;
    }
}
