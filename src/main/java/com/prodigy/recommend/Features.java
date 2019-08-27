package com.prodigy.recommend;

import java.util.HashMap;
import java.util.Map;

public class Features<T> {

    private final Map<T, Point> points;

    private Features(Builder builder) {
        this.points = builder.points;
    }

    public Point get(T data) {
        return points.get(data);
    }

    public class Builder {
        private Map<T, Point> points = new HashMap<>();

        public Builder add(T data, Point point) {
            points.put(data, point);
            return this;
        }

        public Features<T> build() {
            return new Features<>(this);
        }
    }
}
