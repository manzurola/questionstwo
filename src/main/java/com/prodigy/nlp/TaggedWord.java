package com.prodigy.nlp;

import java.util.Objects;

public class TaggedWord extends Word {

    private final String tag;

    public TaggedWord(String word, String tag) {
        super(word);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaggedWord)) return false;
        TaggedWord that = (TaggedWord) o;
        return Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
