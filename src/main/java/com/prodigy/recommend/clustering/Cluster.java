package com.prodigy.recommend.clustering;

import java.util.List;

public class Cluster<T> {

    private final Clusterable<T> center;
    private final List<Clusterable<T>> points;

    public Cluster(Clusterable<T> center, List<Clusterable<T>> points) {
        this.center = center;
        this.points = points;
    }

    public List<Clusterable<T>> getPoints() {
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
