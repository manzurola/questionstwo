package com.prodigy.domain.nlp;

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

    public Sentence deleteWordAt(int index) {
        List<Token> tokens = tokens();
        Token deletedToken = tokens.remove(index);
        if (deletedToken.index() == 0) {
            Token next = tokens.get(index);
            tokens.set(index, next.setBefore(deletedToken.before()));
        }
        else if (tokens.size() > index) {
            int previousIndex = index - 1;
            Token previous = tokens.get(previousIndex);
            tokens.set(previousIndex, previous.setAfter(deletedToken.after()));
        }
        return newSentence(tokens);
    }

    public List<Token> tokens() {
        return words.stream().map(Word::token).collect(Collectors.toList());
    }

    private Sentence newSentence(List<Token> tokens) {
        return factory.fromTokens(tokens);
    }

    public Sentence addWord(int index, String word) {
        List<Token> tokens = tokens();
        Token existingToken = tokens.get(index);
        Token tokenToInsert = existingToken.setValue(word);
        tokens.add(index, tokenToInsert);
        return factory.fromTokens(tokens);
    }

    public Sentence setWord(int index, String word) {
        List<Token> tokens = tokens();
        Token existingToken = tokens.get(index);
        Token replacementToken = existingToken.setValue(word);
        tokens.set(index, replacementToken);
        return factory.fromTokens(tokens);
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
