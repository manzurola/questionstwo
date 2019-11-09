package com.prodigy.domain.questions;

public interface ScoreFactory {

    Score fromTransformationCost(TransformationCost cost);

    Score min();

    Score max();

}
