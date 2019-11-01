package com.prodigy.webapp.api;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class StanfordNlpTest {

    @Test
    public void mainTest() {
        String text = "I go there yesterday.";

        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(text);
        // annnotate the document
        pipeline.annotate(document);
        // examples

        // 10th token of the document
        CoreLabel token = document.tokens().get(0);
        System.out.println("Example: token");
        System.out.println(token);
        System.out.println();

        CoreSentence sentence = document.sentences().get(0);

        // text of sentence
        String sentenceText = sentence.text();
        System.out.println("Example: sentence");
        System.out.println(sentenceText);
        System.out.println();

        // list of the part-of-speech tags
        List<String> posTags = sentence.posTags();
        System.out.println("Example: pos tags");
        System.out.println(posTags);
        System.out.println();

        // list of the ner tags
        List<String> nerTags = sentence.nerTags();
        System.out.println("Example: ner tags");
        System.out.println(nerTags);
        System.out.println();

        // constituency parse]
        Tree constituencyParse = sentence.constituencyParse();
        System.out.println("Example: constituency parse");
        System.out.println(constituencyParse);
        System.out.println();

        // dependency parse
        SemanticGraph dependencyParse = sentence.dependencyParse();
        System.out.println("Example: dependency parse");
        System.out.println(dependencyParse);
        System.out.println();

        // kbp relations
        List<RelationTriple> relations = sentence.relations();
        System.out.println("Example: relation");
        if (!relations.isEmpty())
            System.out.println(relations.get(0));
        System.out.println();

        // entity mentions
        List<CoreEntityMention> entityMentions = sentence.entityMentions();
        System.out.println("Example: entity mentions");
        System.out.println(entityMentions);
        System.out.println();

        // coreference between entity mentions
        if (!sentence.entityMentions().isEmpty()) {
            CoreEntityMention originalEntityMention = sentence.entityMentions().get(0);
            System.out.println("Example: original entity mention");
            System.out.println(originalEntityMention);
            if (originalEntityMention.canonicalEntityMention().isPresent()) {
                System.out.println("Example: canonical entity mention");
                System.out.println(originalEntityMention.canonicalEntityMention().get());
                System.out.println();
            }
        }

        // get document wide coref info
        Map<Integer, CorefChain> corefChains = document.corefChains();
        System.out.println("Example: coref chains for document");
        System.out.println(corefChains);
        System.out.println();

        // get quotes in document
        List<CoreQuote> quotes = document.quotes();
        if (!quotes.isEmpty()) {
            CoreQuote quote = quotes.get(0);
            System.out.println("Example: quote");
            System.out.println(quote);
            System.out.println();

            if (quote.speaker().isPresent()) {
                // original speaker of quote
                // note that quote.speaker() returns an Optional
                System.out.println("Example: original speaker of quote");
                System.out.println(quote.speaker().get());
                System.out.println();
            }

            if (quote.canonicalSpeaker().isPresent()) {
                // canonical speaker of quote
                System.out.println("Example: canonical speaker of quote");
                System.out.println(quote.canonicalSpeaker().get());
                System.out.println();
            }
        }


    }
}
