package com.prodigy.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.prodigy.core.nlp.SentenceParser;

import java.util.concurrent.ExecutionException;


public class SentenceFactory {

    private final SentenceParser parser;
    private final LoadingCache<String, Sentence> sentences;

    public SentenceFactory(SentenceParser parser) {
        this.parser = parser;
        this.sentences = CacheBuilder.newBuilder()
                .build(
                        new CacheLoader<String, Sentence>() {
                            public Sentence load(String key) {
                                return new Sentence(parser.parse(key));
                            }
                        }
                );
    }

    Sentence getSentence(String value) {
        try {
            return sentences.get(value);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}