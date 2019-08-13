package com.prodigy.core.nlp;

import com.prodigy.core.Word;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class PTBSentenceTokenizer implements SentenceTokenizer {
    @Override
    public List<Word> tokenize(String sentence) {
        List<CoreLabel> tokens = new PTBTokenizer<>(new StringReader(sentence), new CoreLabelTokenFactory(true), "invertible").tokenize();
        List<Word> words = new ArrayList<>();
        for (CoreLabel token : tokens) {
            String value = token.word();
            String original = value.concat(token.after());
            words.add(new Word(value, original));
        }
        return words;
    }
}
