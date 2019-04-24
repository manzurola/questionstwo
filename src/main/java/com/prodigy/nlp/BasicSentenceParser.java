package com.prodigy.nlp;

import edu.stanford.nlp.process.WhitespaceTokenizer;

import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

public class BasicSentenceParser implements SentenceParser {

    @Override
    public Sentence parse(String sentence) {
        List<Word> sourceWords = WhitespaceTokenizer.newWordWhitespaceTokenizer(new StringReader(sentence))
                .tokenize()
                .stream()
                .map(w-> new Word(w.word()))
                .collect(Collectors.toList());
        return new Sentence(sentence, sourceWords, null, null);
    }
}
