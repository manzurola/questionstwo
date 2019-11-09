package com.prodigy.domain.nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sentence {

    private final String value;
    private final List<Word> words;
    private final SentenceFactory factory;

    public Sentence(String value, List<Word> words, SentenceFactory factory) {
        this.value = value;
        this.words = Collections.unmodifiableList(words);
        this.factory = factory;
    }

    public String value() {
        return value;
    }

    public List<Word> words() {
        return words;
    }

    public Word wordAt(int index) {
        return words.get(index);
    }

    public Sentence deleteWord(Word word) {
        if (words.contains(word)) {
            List<Word> newWords = words.stream().filter(word::equals).collect(Collectors.toList());
            return factory.fromString(assembleValue(newWords));
        }
        return this;
    }

    public Sentence deleteWordAt(int index) {
        List<Word> newWords = new ArrayList<>(words);
        newWords.remove(index);
        return factory.fromString(assembleValue(newWords));
    }

    private String assembleValue(List<Word> words) {
        StringBuilder builder = new StringBuilder();
        for (Word word : words) {
            builder.append(word.original());
        }
        return builder.toString();
    }

    public Sentence insertWord(int index, Word word) {
        List<Word> newWords = new ArrayList<>(words);
        newWords.add(index, word);
        return factory.fromString(assembleValue(newWords));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(value, sentence.value) &&
                Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, words);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "text='" + value + '\'' +
                ", words=" + words +
                '}';
    }
}
