package com.prodigy.nlp;

import edu.stanford.nlp.process.WhitespaceTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BasicSentenceParser implements SentenceParser {

    private final static Logger log = LoggerFactory.getLogger(BasicSentenceParser.class);

    @Override
    public Sentence parse(String sentence) {
        log.info("Sentence to parse: " + sentence);
        String[] split = sentence.replaceAll("(^\\h*)|(\\h*$)","").split(" ");
        log.info("The split sentence: " + Arrays.toString(split));
        List<Word> sourceWords = WhitespaceTokenizer.newWordWhitespaceTokenizer(new StringReader(sentence))
                .tokenize()
                .stream()
                .map(w-> new Word(w.word()))
                .collect(Collectors.toList());
        log.info("Parsed words: " + sourceWords);
        return new Sentence(sentence, null, null);
    }
}
