package com.prodigy.nlp;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StanfordNLPTokenizer implements Tokenizer {
    @Override
    public List<Sentence> tokenize(String text) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
        for (List<HasWord> words : tokenizer) {
            Sentence sentence = new Sentence(words.stream().map(w -> new Word(w.word())).collect(Collectors.toList()));
            sentences.add(sentence);
        }
        return sentences;
    }
}
