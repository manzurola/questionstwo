package com.prodigy.core.nlp;

import com.prodigy.core.Word;

import java.util.List;

public interface SentenceTokenizer {

    List<Word> tokenize(String sentence);
}
