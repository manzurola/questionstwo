package com.prodigy.core.scoring;

import java.util.Objects;

public class Point {
    private final double value;
    private final double weight;

    public Point(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public double value() {
        return value;
    }

    public double weight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return value == point.value &&
                Double.compare(point.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, weight);
    }

    @Override
    public String toString() {
        return "Point{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }
}