package com.prodigy.nlp;

import java.util.List;
import java.util.Objects;

public class Sentence {

    private final String value;
    private final List<TaggedWord> words;
    private final List<GrammaticalRelation> relations;

    public Sentence(String value, List<TaggedWord> words, List<GrammaticalRelation> relations) {
        this.value = value;
        this.words = words;
        this.relations = relations;
    }

    public List<TaggedWord> getWords() {
        return words;
    }

    public List<GrammaticalRelation> getRelations() {
        return relations;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + words +
                ", relations=" + relations +
                '}';
    }
}
