package com.prodigy.engine;

import java.util.Arrays;

public class FeatureVector<T> {

    private final double[] point;
    private final T data;

    public FeatureVector(double[] point, T data) {
        this.point = point;
        this.data = data;
    }

    public double[] getPoint() {
        return point;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeatureVector)) return false;
        FeatureVector<?> that = (FeatureVector<?>) o;
        return Arrays.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(point);
    }

    @Override
    public String toString() {
        return "FeatureVector{" +
                "point=" + Arrays.toString(point) +
                ", data=" + data +
                '}';
    }
}
