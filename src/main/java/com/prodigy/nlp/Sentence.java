package com.prodigy.nlp;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sentence {

    private final String value;
    private final List<Word> words;
    private final List<TaggedWord> taggedWords;
    private final List<GrammaticalRelation> relations;

    public Sentence(String value, List<TaggedWord> taggedWords, List<GrammaticalRelation> relations) {
        this.value = value;
        this.words = taggedWords.stream().map(w -> new Word(w.value())).collect(Collectors.toList());
        this.taggedWords = taggedWords;
        this.relations = relations;
    }

    public List<TaggedWord> getTaggedWords() {
        return taggedWords;
    }

    public List<GrammaticalRelation> getRelations() {
        return relations;
    }

    public String getValue() {
        return value;
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(value, sentence.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
