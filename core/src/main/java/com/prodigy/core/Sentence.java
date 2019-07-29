package com.prodigy.core;

import com.prodigy.core.nlp.GrammaticalRelation;
import com.prodigy.core.nlp.IndexedWord;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


public class Sentence implements Iterable<Word>, Serializable {

    private final List<Word> words;
    private final List<GrammaticalRelation> grammaticalRelations;

    public Sentence(List<Word> words, List<GrammaticalRelation> grammaticalRelations) {
        this.words = Collections.unmodifiableList(words);
        this.grammaticalRelations = grammaticalRelations;
    }

    @Override
    public Iterator<Word> iterator() {
        return words.iterator();
    }

    public Word getWordAt(int index) {
        return words.get(index);
    }

    public List<Word> getWords() {
        return words;
    }

    public List<GrammaticalRelation> getRelationsForWordAt(int index) {
        IndexedWord word = new IndexedWord(index + 1, words.get(index));// relations add an extra word as the governor of root
        return grammaticalRelations.stream()
                .filter(relation -> word.equals(relation.dependant()) || word.equals(relation.governor()))
                .collect(Collectors.toList());
    }

    public int size() {
        return words.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence words1 = (Sentence) o;
        return Objects.equals(words, words1.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + words +
                '}';
    }
}
