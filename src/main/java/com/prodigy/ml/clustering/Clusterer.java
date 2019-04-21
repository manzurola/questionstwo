package com.prodigy.ml.clustering;

import com.prodigy.ml.FeatureVector;

import java.util.Collection;
import java.util.List;

public interface Clusterer<T> {

    List<Cluster<T>> cluster(Collection<FeatureVector<T>> points) throws Exception;
}
