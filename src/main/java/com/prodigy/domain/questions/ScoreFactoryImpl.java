package com.prodigy.domain.questions;

public class ScoreFactoryImpl implements ScoreFactory {

    private final Score min = new Score(0);
    private final Score max = new Score(100);

    //(((OldValue - OldMin) * (NewMax - NewMin)) / (OldMax - OldMin)) + NewMin
    @Override
    public Score fromTransformationCost(TransformationCost cost) {
        double oldMin = TransformationCost.min;
        double oldMax = TransformationCost.max;
        double oldValue = oldMax - cost.value(); // turn low cost (good) to larger number
        double newMax = max.total();
        double newMin = min.total();
        double adjustedScore = (((oldValue - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
        return new Score(adjustedScore);
    }

    @Override
    public Score min() {
        return min;
    }

    @Override
    public Score max() {
        return max;
    }
}
