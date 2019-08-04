package com.prodigy.engine;

import com.prodigy.engine.tokenize.SentenceTokenizer;
import com.prodigy.engine.tokenize.TaggingTokenizer;

public class DefaultSentenceFactory implements SentenceFactory {

    private final SentenceTokenizer tokenizer;

    public DefaultSentenceFactory() {
        this.tokenizer = new TaggingTokenizer();
    }

    public DefaultSentenceFactory(SentenceTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public Sentence parse(String text) {
        return null;
    }
}
