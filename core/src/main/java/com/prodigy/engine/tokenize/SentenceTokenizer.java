package com.prodigy.engine.tokenize;

import com.prodigy.engine.Word;

import java.util.List;

public interface SentenceTokenizer {
    
    List<Word> tokenize(String sentence);
}
