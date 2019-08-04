package com.prodigy.engine.nlp;

import java.util.Objects;

public class GrammaticalRelation {

    private final String name;
    private final IndexedWord governor;
    private final IndexedWord dependant;

    private GrammaticalRelation(Builder builder) {
        this.name = builder.name;
        this.governor = builder.governor;
        this.dependant = builder.dependant;
    }

    public GrammaticalRelation(String name, IndexedWord governor, IndexedWord dependant) {
        this.name = name;
        this.governor = governor;
        this.dependant = dependant;
    }

    public String getName() {
        return name;
    }

    public IndexedWord governor() {
        return governor;
    }

    public IndexedWord dependant() {
        return dependant;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private IndexedWord governor;
        private IndexedWord dependant;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withGovernor(IndexedWord word) {
            this.governor = word;
            return this;
        }

        public Builder withDependant(IndexedWord word) {
            this.dependant = word;
            return this;
        }

        public GrammaticalRelation build() {
            return new GrammaticalRelation(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrammaticalRelation)) return false;
        GrammaticalRelation that = (GrammaticalRelation) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(governor, that.governor) &&
                Objects.equals(dependant, that.dependant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, governor, dependant);
    }

    @Override
    public String toString() {
        return "GrammaticalRelation{" +
                "name='" + name + '\'' +
                ", governor=" + governor +
                ", dependant=" + dependant +
                '}';
    }
}
