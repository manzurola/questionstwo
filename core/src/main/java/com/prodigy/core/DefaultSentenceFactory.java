package com.prodigy.core;

import com.prodigy.core.tokenize.SentenceTokenizer;
import com.prodigy.core.tokenize.TaggingTokenizer;

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
