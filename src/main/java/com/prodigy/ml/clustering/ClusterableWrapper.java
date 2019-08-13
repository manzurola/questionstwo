package com.prodigy.ml.clustering;

import com.prodigy.ml.FeatureVector;

class ClusterableWrapper<T> implements org.apache.commons.math3.ml.clustering.Clusterable {

    private final FeatureVector<T> vector;

    ClusterableWrapper(FeatureVector<T> vector) {
        this.vector = vector;
    }

    @Override
    public double[] getPoint() {
        return vector.getPoint();
    }

    public T getData() {
        return vector.getData();
    }
}
