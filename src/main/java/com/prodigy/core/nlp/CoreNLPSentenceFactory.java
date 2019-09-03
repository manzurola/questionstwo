package com.prodigy.core.nlp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.prodigy.core.Sentence;
import com.prodigy.core.SentenceFactory;
import com.prodigy.core.Word;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class CoreNLPSentenceFactory implements SentenceFactory {

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

    public CoreNLPSentenceFactory() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        this.pipeline = new StanfordCoreNLP(props);
    }

    @Override
    public Sentence getSentence(String value) {
        try {
            return sentences.get(value);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Sentence createSentence(String value) {
        CoreDocument document = new CoreDocument(value);
        pipeline.annotate(document);
        if (document.sentences().isEmpty()) {
            return new Sentence(Collections.emptyList());
        }
        if (document.sentences().size() > 1) {
            throw new RuntimeException("Value must contain exactly one sentence: [" + value + "]");
        }
        CoreSentence core = document.sentences().get(0);
        List<Word> words = new ArrayList<>();
        for (CoreLabel token : core.tokens()) {
            words.add(Word.newBuilder()
                    .value(token.value())
                    .original(token.value().concat(token.after()))
                    .index(token.index())
                    .posTag(POS.ofTag(token.tag()))
                    .build()
            );
        }

        return new Sentence(words);
    }
}
