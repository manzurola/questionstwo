package com.prodigy.core.nlp;

import com.prodigy.core.Word;
import edu.stanford.nlp.process.WhitespaceTokenizer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class WhitespaceSentenceTokenizer implements SentenceTokenizer {
    @Override
    public List<Word> tokenize(String sentence) {
        List<edu.stanford.nlp.ling.Word> tokens = WhitespaceTokenizer.newWordWhitespaceTokenizer(new StringReader(sentence)).tokenize();
        List<Word> words = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            edu.stanford.nlp.ling.Word token = tokens.get(i);
            String originalValue = i == tokens.size() - 1 ? token.value() : token.value() + " ";
            words.add(new Word(token.value(), originalValue));
        }
        return words;
    }
}
