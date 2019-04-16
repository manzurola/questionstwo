package com.prodigy.nlp;

import java.util.Objects;

public class TaggedWord extends Word {

    private final POS tag;
    private final int index;

    public TaggedWord(String word, POS tag, int index) {
        super(word);
        this.tag = tag;
        this.index = index;
    }

    public POS tag() {
        return tag;
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaggedWord)) return false;
        if (!super.equals(o)) return false;
        TaggedWord that = (TaggedWord) o;
        return index == that.index &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tag, index);
    }

    @Override
    public String toString() {
        return String.format("[%s-%d/%s]", word(), index, tag != null ? tag.name() : "");
    }
}
