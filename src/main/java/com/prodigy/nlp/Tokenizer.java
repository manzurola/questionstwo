package com.prodigy.nlp;

import java.util.List;

public interface Tokenizer {

    List<Sentence> tokenize(String text);
}
