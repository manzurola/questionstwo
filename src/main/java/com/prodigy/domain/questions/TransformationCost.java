package com.prodigy.domain.questions;

import java.util.Objects;

public class TransformationCost {

    public static final double min = 0;
    public static final double max = 1;
    private final double value;

    public TransformationCost(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransformationCost that = (TransformationCost) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "TransformationCost{" +
                "value=" + value +
                '}';
    }
}
