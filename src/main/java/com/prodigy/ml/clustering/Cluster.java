package com.prodigy.ml.clustering;

import com.prodigy.ml.FeatureVector;

import java.util.List;

public class Cluster<T> {

    private final FeatureVector<T> center;
    private final List<FeatureVector<T>> points;

    public Cluster(FeatureVector<T> center, List<FeatureVector<T>> points) {
        this.center = center;
        this.points = points;
    }

    public FeatureVector<T> getCenter() {
        return center;
    }

    public List<FeatureVector<T>> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "center=" + center +
                ", points=" + points +
                '}';
    }
}
