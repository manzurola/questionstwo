package com.prodigy.core.scoring;

import java.util.List;

public class Score {
    private final List<Point> points;

    public Score(List<Point> points) {
        this.points = points;
    }

    public List<Point> points() {
        return points;
    }

    public double average() {
        double sumOfWeights = 0;
        double sumOfValueTimesWeight = 0;
        for (Point point : points) {
            sumOfValueTimesWeight += point.value() * point.weight();
            sumOfWeights += point.weight();
        }

        return  sumOfValueTimesWeight / sumOfWeights;
    }
}
