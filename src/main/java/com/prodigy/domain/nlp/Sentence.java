package com.prodigy.domain.nlp;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Sentence {

    private final String value;
    private final List<Word> words;
    private final transient SentenceFactory factory;

    public Sentence(String value, List<Word> words, SentenceFactory factory) {
        this.value = value;
        this.words = Collections.unmodifiableList(words);
        this.factory = factory;
    }

    public final String value() {
        return value;
    }

    public final List<Word> words() {
        return words;
    }

    public final Word wordAt(int index) {
        return words.get(index);
    }

    public final Sentence deleteWordAt(int index) {
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

    public final Sentence addWord(int index, String word) {
        List<Token> tokens = tokens();
        Token existingToken = tokens.get(index);
        Token tokenToInsert = existingToken.setValue(word);
        tokens.add(index, tokenToInsert);
        return factory.fromTokens(tokens);
    }

    public final Sentence addWord(String word) {
        List<Token> tokens = tokens();
        Token lastToken = tokens.get(tokens.size() - 1);
        Token tokenToInsert = lastToken.setValue(word);
        tokens.add(tokenToInsert);
        return factory.fromTokens(tokens);
    }

    public final Sentence setWord(int index, String word) {
        List<Token> tokens = tokens();
        Token existingToken = tokens.get(index);
        Token replacementToken = existingToken.setValue(word);
        tokens.set(index, replacementToken);
        return factory.fromTokens(tokens);
    }

    public final int size() {
        return words.size();
    }

    private List<Token> tokens() {
        return words.stream().map(Word::token).collect(Collectors.toList());
    }

    private Sentence newSentence(List<Token> tokens) {
        return factory.fromTokens(tokens);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(value, sentence.value) &&
                Objects.equals(words, sentence.words);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value, words);
    }

    @Override
    public final String toString() {
        return "Sentence{" +
                "text='" + value + '\'' +
                ", words=" + words +
                '}';
    }
}
