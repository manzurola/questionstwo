package com.prodigy.core.nlp;

import com.prodigy.core.Sentence;
import com.prodigy.core.SentenceFactory;
import com.prodigy.core.Word;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.*;

public class CoreNLPSentenceFactory implements SentenceFactory {

    private final StanfordCoreNLP pipeline;

    public CoreNLPSentenceFactory() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos");
        this.pipeline = new StanfordCoreNLP(props);
    }

    @Override
    public Sentence getSentence(String value) {
        CoreDocument document = new CoreDocument(value);
        pipeline.annotate(document);
        if (document.sentences().isEmpty()) {
            return new Sentence(Collections.emptyList());
        }
        if (document.sentences().size() > 1) {
            throw new RuntimeException("Value must contain exactly one sentence.");
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
