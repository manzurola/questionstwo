package com.prodigy.core;

import com.prodigy.core.grammar.GrammaticalRelation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GrammaticalRelationsVector  {
    private final double[] point = new double[50];

    public GrammaticalRelationsVector(List<GrammaticalRelation> grammar) {

        Arrays.fill(point, 0);

        for (GrammaticalRelation relation : grammar) {
            String relationName = relation.getName().toUpperCase();
            Optional<TypedDependency> opt = TypedDependency.ofValue(relationName);
            if (!opt.isPresent()) {
                throw new RuntimeException(String.format("Could not instantiate class, failed to resolve typed dependency %s", relation.getName()));
            }
            TypedDependency dependency = opt.get();
            point[dependency.index()] += 1;
        }
    }

    public double[] getPoint() {
        return point;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrammaticalRelationsVector that = (GrammaticalRelationsVector) o;
        return Arrays.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(point);
    }

    @Override
    public String toString() {
        return "GrammaticalRelationsVector{" +
                "point=" + Arrays.toString(point) +
                '}';
    }
}
