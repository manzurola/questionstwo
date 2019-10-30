package com.prodigy.grammar;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Sentence {

    private final String text;
    private final List<Word> words;

    public Sentence(String text, List<Word> words) {
        this.text = text;
        this.words = Collections.unmodifiableList(words);
    }

    public String text() {
        return text;
    }

    public List<Word> words() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(text, sentence.text) &&
                Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, words);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "text='" + text + '\'' +
                ", words=" + words +
                '}';
    }
}
