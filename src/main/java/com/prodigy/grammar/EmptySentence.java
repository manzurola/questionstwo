package com.prodigy.grammar;

import java.util.Collections;
import java.util.List;

public class EmptySentence implements Sentence {
    @Override
    public String text() {
        return "";
    }

    @Override
    public List<Word> words() {
        return Collections.emptyList();
    }
}
