package com.prodigy.recommend.clustering;

import java.util.Collection;
import java.util.List;

public interface Clusterer<T> {

    List<Cluster<T>> cluster(Collection<Clusterable<T>> points) throws Exception;
}
