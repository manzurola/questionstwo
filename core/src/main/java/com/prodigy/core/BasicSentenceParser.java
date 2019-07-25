package com.prodigy.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicSentenceParser implements POSTagger {

    private final static Logger log = LoggerFactory.getLogger(BasicSentenceParser.class);

    @Override
    public Sentence tag(String sentence) {

        String[] split = sentence.replaceAll("(^\\h*)|(\\h*$)","").split(" ");

//        log.info("Sentence to tag: " + sentence);
//        List<Word> sourceWords = WhitespaceTokenizer.newWordWhitespaceTokenizer(new StringReader(sentence))
//                .tokenize()
//                .stream()
//                .map(w-> new Word(w.word()))
//                .collect(Collectors.toList());
//        log.info("Parsed words: " + sourceWords);
//        return new Sentence(sentence, sourceWords, null, null);
        return null;
    }
}
