package com.prodigy.recommend.clustering;

import com.prodigy.recommend.Point;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.ManhattanDistance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ApacheMLClusterer<T> implements Clusterer<T> {

    private final KMeansPlusPlusClusterer<ClusterableWrapperAdapter<T>> clusterer;

    public ApacheMLClusterer() {
        this(new ManhattanDistance());
    }

    public ApacheMLClusterer(DistanceMeasure distance) {
        this.clusterer = new KMeansPlusPlusClusterer<>(20, 10000, distance);
    }


    private Cluster<T> adaptCluster(CentroidCluster<ClusterableWrapperAdapter<T>> source) {
        org.apache.commons.math3.ml.clustering.Clusterable center = source.getCenter();
        Clusterable<T> vectorCenter = new Clusterable<T>(new Point(center.getPoint()), null); // no data for center
        List<Clusterable<T>> vectors = new ArrayList<>();
        for (ClusterableWrapperAdapter<T> point : source.getPoints()) {
            vectors.add(new Clusterable<T>(new Point(point.getPoint()), point.getData()));
        }

        return new Cluster<T>(vectorCenter, vectors);
    }

    @Override
    public List<Cluster<T>> cluster(Collection<Clusterable<T>> points) throws Exception {
        List<CentroidCluster<ClusterableWrapperAdapter<T>>> clusterResults = clusterer.cluster(
                points
                        .stream()
                        .map(ClusterableWrapperAdapter::new)
                        .collect(Collectors.toList())
        );

        return clusterResults.stream()
                .map(this::adaptCluster)
                .collect(Collectors.toList());

    }


    private static class ClusterableWrapperAdapter<T> implements org.apache.commons.math3.ml.clustering.Clusterable {

        private final Clusterable<T> clusterable;

        public ClusterableWrapperAdapter(Clusterable<T> clusterable) {
            this.clusterable = clusterable;
        }

        @Override
        public double[] getPoint() {
            return clusterable.point();
        }

        public T getData() {
            return clusterable.data();
        }
    }
}
