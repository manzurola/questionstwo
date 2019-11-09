package com.prodigy.domain.nlp.impl;

import com.prodigy.domain.nlp.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class CoreNLPSentenceFactory implements SentenceFactory {

    private final StanfordCoreNLP pipeline;

    public CoreNLPSentenceFactory(StanfordCoreNLP pipeline) {
        this.pipeline = pipeline;
    }

    public CoreNLPSentenceFactory() {
        this.pipeline = createDefaultPipeline();
    }

    private StanfordCoreNLP createDefaultPipeline() {
        Properties props = new Properties();
        // TODO read https://stanfordnlp.github.io/CoreNLP/ssplit.html, there is an option to treat document as one sentence (ssplit)
        props.setProperty("annotators", "tokenize,ssplit,pos");
        props.setProperty("ssplit.isOneSentence", "true");
        return new StanfordCoreNLP(props);
    }

    @Override
    public Sentence fromString(String value) {
        List<Word> words = parseWords(value);
        return new Sentence(value, words, this);
    }

    private List<Word> parseWords(String sentence) {
        CoreDocument document = parseCoreDocument(sentence);
        if (document.sentences().isEmpty()) {
            return Collections.emptyList();
        }
        CoreSentence firstSentence = document.sentences().get(0);
        return parseWords(firstSentence);
    }

    private CoreDocument parseCoreDocument(String text) {
        CoreDocument document = new CoreDocument(text);

        pipeline.annotate(document);
        return document;
    }

    private List<Word> parseWords(CoreSentence sentence) {
        return sentence.tokens()
                .stream()
                .map(this::newWord)
                .collect(Collectors.toList());
    }

    private Word newWord(CoreLabel label) {
        Token token = new Token(label.value(), label.before(), label.after(), label.index() - 1);
        return new Word(token, POS.ofTag(label.tag()));
    }

    @Override
    public Sentence fromTokens(List<Token> tokens) {
        return fromString(assembleValueFromTokens(tokens));
    }

    private String assembleValueFromTokens(List<Token> tokens) {
        StringBuilder builder = new StringBuilder();
        for (Token token : tokens) {
            builder.append(token.original());
        }
        return builder.toString();
    }
}
