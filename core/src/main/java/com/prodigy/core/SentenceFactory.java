package com.prodigy.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.prodigy.core.tokenize.TaggingTokenizer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;


public class SentenceFactory {

    private final TaggingTokenizer tokenizer;
    private final LoadingCache<String, Sentence> sentences;

    public SentenceFactory() {
        this(new TaggingTokenizer());
    }

    private SentenceFactory(TaggingTokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.sentences = CacheBuilder.newBuilder()
                .build(
                        new CacheLoader<String, Sentence>() {
                            public Sentence load(String key) {
                                return new Sentence(tokenizer.tokenize(key));
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