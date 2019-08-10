package com.prodigy.core.nlp;

import com.prodigy.core.Word;

import java.util.List;

public interface SentenceParser {
    
    List<Word> parse(String sentence);
}
