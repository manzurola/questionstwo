package com.prodigy.core;

import com.prodigy.core.nlp.POS;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sentence {

    private final List<Word> words;

    public Sentence(List<Word> words) {
        this.words = Collections.unmodifiableList(words);
    }

    public List<Word> words() {
        return words;
    }

    public Word wordAt(int i) {
        return words.get(i);
    }

    public POS posTag(int i) {
        return words.get(i).posTag();
    }

    public List<POS> posTags() {
        return words.stream().map(Word::posTag).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }
}
