package com.prodigy.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Word implements Taggable {

    private final String value;
    private final int index;
    private final Set<Tag> tags;

    public Word(String value, int index, Set<Tag> tags) {
        this.tags = tags;
        this.index = index;
        this.value = value;
    }

    public String value() {
        return value;
    }

    public boolean containsTag(Tag tag) {
        return tags.contains(tag);
    }

    public List<Tag> tagList() {
        return new ArrayList<>(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }
}
