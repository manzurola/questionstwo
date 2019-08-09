package com.prodigy.core.tokenize;

import com.prodigy.core.Word;

import java.util.List;

public interface SentenceTokenizer {
    
    List<Word> tokenize(String sentence);
}
