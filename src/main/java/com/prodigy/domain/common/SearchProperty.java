package com.prodigy.domain.common;

import java.util.Objects;

public class SearchProperty<T> {

    private final String name;
    private final T value;

    public SearchProperty(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchProperty)) return false;
        SearchProperty<?> that = (SearchProperty<?>) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
