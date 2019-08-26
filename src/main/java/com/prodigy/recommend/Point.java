package com.prodigy.recommend;

import java.util.Arrays;

public class Point {

    private final double[] point;

    public Point(double[] point) {
        this.point = point;
    }

    public double[] getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
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
