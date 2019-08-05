package com.prodigy.ml.clustering;

import com.prodigy.ml.FeatureVector;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ApacheMLClusterer<T> implements Clusterer<T> {

    private final KMeansPlusPlusClusterer<ClusterableWrapper> clusterer;

    public ApacheMLClusterer() {
        this(new ManhattanDistance());
    }

    public ApacheMLClusterer(DistanceMeasure distance) {
        this.clusterer = new KMeansPlusPlusClusterer<>(20, 10000, distance);
    }


    private Cluster<T> adaptCluster(CentroidCluster<ClusterableWrapper> source) {
        Clusterable center = source.getCenter();
        FeatureVector<T> vectorCenter = new FeatureVector<>(center.getPoint(), null);
        List<FeatureVector<T>> vectors = new ArrayList<>();
        for (ClusterableWrapper point : source.getPoints()) {
            vectors.add(new FeatureVector<>(point.getPoint(), point.getData()));
        }

        return new Cluster<>(vectorCenter, vectors);
    }

    @Override
    public List<Cluster<T>> cluster(Collection<FeatureVector<T>> points) throws Exception {
        List<CentroidCluster<ClusterableWrapper>> clusterResults = clusterer.cluster(
                points
                        .stream()
                        .map(ClusterableWrapper::new)
                        .collect(Collectors.toList()));

        return clusterResults.stream()
                .map(this::adaptCluster)
                .collect(Collectors.toList());

    }


    private class ClusterableWrapper implements org.apache.commons.math3.ml.clustering.Clusterable {

        private final FeatureVector<T> vector;

        public ClusterableWrapper(FeatureVector<T> vector) {
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
}
