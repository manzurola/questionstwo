package com.prodigy.core;

import java.util.Arrays;

public class FeatureVector {

    private final double[] point;

    public FeatureVector(double[] point) {
        this.point = point;
    }

    public double[] point() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeatureVector)) return false;
        FeatureVector that = (FeatureVector) o;
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
                '}';
    }
}
