package com.prodigy.core.nlp;

import java.util.List;

public interface SentenceTokenizer {

    List<String> tokenize(String sentence);
}
