package com.prodigy.domain;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.simple.Sentence;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

public class PipelineTest {

    @Test
    public void name() {

        String text = "My friend may be coming over later tonight.";

        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        props.setProperty("annotators", "tokenize,ssplit,pos");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
//        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(text);
        // annnotate the document
        pipeline.annotate(document);
        // examples

        CoreSentence sentence = document.sentences().get(0);

        // 10th token of the document
        for (CoreLabel token : sentence.tokens()) {
            System.out.println(String.format("word: '%s', ner: '%s', tag: '%s', index: %d, begin: %d, end: %d, before: '%s', after: '%s'", token.word(), token.ner(), token.tag(), token.index(), token.beginPosition(), token.endPosition(), token.before(), token.after()));
        }

    }

    @Test
    public void simple() {
        Sentence sent = new Sentence("Lucy is in the sky with diamonds.");
        List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
        String firstPOSTag = sent.posTag(0);   // NNP

        System.out.println(sent);
        System.out.println("read first sentence");

        Sentence sent2 = new Sentence("My friend Alon should be coming over later today.");
        System.out.println(sent2);

    }
}
