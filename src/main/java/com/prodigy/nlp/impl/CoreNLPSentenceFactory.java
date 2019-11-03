package com.prodigy.nlp.impl;

import com.prodigy.nlp.*;
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

    @Override
    public Sentence fromString(String value) {
        List<Word> words = parseWords(value);
        return new Sentence(value, words);
    }

    private StanfordCoreNLP createDefaultPipeline() {
        Properties props = new Properties();
        // TODO read https://stanfordnlp.github.io/CoreNLP/ssplit.html, there is an option to treat document as one sentence (ssplit)
        props.setProperty("annotators", "tokenize,ssplit,pos");
        return new StanfordCoreNLP(props);
    }

    private List<Word> parseWords(String sentence) {
        CoreDocument document = parseCoreDocument(sentence);
        if (documentIsEmpty(document)) {
            return Collections.emptyList();
        }
        if (documentContainsMultipleSentences(document)) {
            throw new RuntimeException("Value must contain exactly one sentence");
        }
        CoreSentence firstSentence = getFirstSentence(document);
        return parseWords(firstSentence);
    }

    private CoreDocument parseCoreDocument(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return document;
    }

    private boolean documentIsEmpty(CoreDocument document) {
        return document.sentences().isEmpty();
    }

    private boolean documentContainsMultipleSentences(CoreDocument document) {
        return document.sentences().size() > 1;
    }

    private CoreSentence getFirstSentence(CoreDocument document) {
        return document.sentences().get(0);
    }

    private List<Word> parseWords(CoreSentence sentence) {
        return sentence.tokens()
                .stream()
                .map(this::newWord)
                .collect(Collectors.toList());
    }

    private Word newWord(CoreLabel label) {
        return new Word(label.value(), label.index(), POS.ofTag(label.tag()), label.value().concat(label.after()));
    }
}
