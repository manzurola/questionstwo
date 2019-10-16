package com.prodigy.grammar.corenlp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.prodigy.grammar.EmptySentence;
import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.SentenceFactory;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class CoreSentenceWrapperFactory implements SentenceFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StanfordCoreNLP pipeline;
    private final LoadingCache<String, Sentence> sentences = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build(
                    new CacheLoader<String, Sentence>() {
                        public Sentence load(String key) throws Exception {
                            return createSentence(key);
                        }
                    }
            );

    public CoreSentenceWrapperFactory() {
        this.pipeline = createDefaultPipeline();
    }

    public CoreSentenceWrapperFactory(StanfordCoreNLP pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public Sentence getSentence(String value) {
        try {
            return sentences.get(value);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Sentence createSentence(String text) {
        CoreDocument document = parseDocument(text);
        if (documentIsEmpty(document)) {
            return new EmptySentence();
        }
        if (documentContainsMultipleSentences(document)) {
            throw new RuntimeException("Value must contain exactly one sentence");
        }
        return new CoreSentenceWrapper(getFirstSentence(document));
    }

    private CoreDocument parseDocument(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return document;
    }

    private CoreSentence getFirstSentence(CoreDocument document) {
        return document.sentences().get(0);
    }

    private boolean documentContainsMultipleSentences(CoreDocument document) {
        return document.sentences().size() > 1;
    }

    private boolean documentIsEmpty(CoreDocument document) {
        return document.sentences().isEmpty();
    }

    private StanfordCoreNLP createDefaultPipeline() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        return new StanfordCoreNLP(props);
    }
}
