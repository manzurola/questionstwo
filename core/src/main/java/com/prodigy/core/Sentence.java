package com.prodigy.core;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


public class Sentence implements Iterable<Word>, Serializable {

    private final List<Word> words;

    public Sentence(List<Word> words) {
        this.words = words;
    }

    @Override
    public Iterator<Word> iterator() {
        return words.iterator();
    }

    public Word getWordAt(int index) {
        return words.get(index);
    }

    public int size() {
        return words.size();
    }

}
