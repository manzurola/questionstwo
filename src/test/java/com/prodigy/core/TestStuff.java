package com.prodigy.core;

import com.prodigy.core.diff.DMPDiffCalculator;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.diff.DiffCalculator;
import com.prodigy.core.nlp.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.*;

public class TestStuff {

    @Test
    public void testTokenize() {
        String text = "Doesn't he love his wife?";
        List<edu.stanford.nlp.ling.Word> words = WhitespaceTokenizer.factory().getTokenizer(new StringReader(text)).tokenize();
        System.out.println(words);

        List<edu.stanford.nlp.ling.Word> ptb = PTBTokenizer.factory().getTokenizer(new StringReader(text)).tokenize();
        System.out.println(ptb);

        List<CoreLabel> coreLabels = new PTBTokenizer<>(new StringReader(text), new CoreLabelTokenFactory(true), "invertible").tokenize();
        for (CoreLabel coreLabel : coreLabels) {
            System.out.println(String.format("word: %s, ner: %s, tag: %s, index: %d, begin: %d, end: %d, before: %s, after: %s", coreLabel.word(), coreLabel.ner(), coreLabel.tag(), coreLabel.index(), coreLabel.beginPosition(), coreLabel.endPosition(), coreLabel.before(), coreLabel.after()));
        }

        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        List<TaggedWord> taggedWords = tagger.tagSentence(coreLabels);
        System.out.println(taggedWords);
    }

    @Test
    public void testLargeDiff() {
        List<String> target = new ArrayList<>();
        for (int i = 0; i < Character.MAX_VALUE; i++) {
             target.add(String.valueOf(Math.random()));
        }

        List<String> source = new ArrayList<>();

        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();
        List<Diff<String>> actual = diffCalculator.getDiff(source, target);
        System.out.println(actual);
    }

    @Test
    public void testParseAndDiff() {
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        SentenceParser parser = new StanfordSentenceParser(tagger);

        String target = "Does n't he love his wife?";
        String answer = "Doesn't he love his wife ?";

        List<Word> targetWords = parser.parse(target);
        List<Word> answerWords = parser.parse(answer);

        DiffCalculator diffCalculator = new DMPDiffCalculator(new DiffMatchPatch());
        List<Diff<Word>> diff = diffCalculator.getDiff(targetWords, answerWords);

        System.out.println(diff);


        System.out.println(parser.parse("you haven't missed me at all"));
    }

    @Test
    public void testDiff() {


        List<Elem> target = Arrays.asList(new Elem("hello"), new Elem("my"), new Elem("name"), new Elem("is"), new Elem("guy"));
        List<Elem> source = Arrays.asList(new Elem("hello"), new Elem("name"), new Elem("is"), new Elem("guy"));

        List<Diff<Elem>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.EQUAL, new Elem("hello")),
                new Diff<>(Diff.Operation.INSERT, new Elem("my")),
                new Diff<>(Diff.Operation.EQUAL, new Elem("name")),
                new Diff<>(Diff.Operation.EQUAL, new Elem("is")),
                new Diff<>(Diff.Operation.EQUAL, new Elem("guy"))
        );

        DiffCalculator diffCalculator = new DMPDiffCalculator();
        List<Diff<Elem>> actual = diffCalculator.getDiff(source, target);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void grammarRelations() {
        String sentence = "hello my name is Guy and I am happy.";
        StanfordSentenceParser tokenizer = new StanfordSentenceParser();
        List<Word> words = tokenizer.parse(sentence);
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        List<GrammaticalRelation> relations = parser.parse(words);
        System.out.println(relations);
    }

    @Test
    public void testRelationalDiffEquals() {
        StanfordSentenceParser tokenizer = new StanfordSentenceParser();
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();

        String target = "hello my name is Guy and I am happy.";
        List<Word> targetWords = tokenizer.parse(target);
        List<GrammaticalRelation> targetRel = parser.parse(targetWords);
        System.out.println(targetRel);

        String source = "hello my name is Guy and I am happy.";
        tokenizer = new StanfordSentenceParser();
        List<Word> sourceWords = tokenizer.parse(source);
        List<GrammaticalRelation> sourceRel = parser.parse(sourceWords);
        System.out.println(sourceRel);

        List<Diff<GrammaticalRelation>> diff = diffCalculator.getDiff(sourceRel, targetRel);
        System.out.println(diff);
    }

    @Test
    public void testRelationalDiff() {
        StanfordSentenceParser tokenizer = new StanfordSentenceParser();
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();

        String target = "hello my name is Guy and I am happy.";
        List<Word> targetWords = tokenizer.parse(target);
        List<GrammaticalRelation> targetRel = parser.parse(targetWords);
        System.out.println(targetWords);

        String source = "hello my is name is Guy and I happy.";
        tokenizer = new StanfordSentenceParser();
        List<Word> sourceWords = tokenizer.parse(source);
        List<GrammaticalRelation> sourceRel = parser.parse(sourceWords);
        System.out.println(sourceWords);

        List<Diff<Word>> diff = diffCalculator.getDiff(sourceWords, targetWords);

        Sentence targetSentence = new Sentence(targetWords, targetRel);
        Sentence sourceSentence = new Sentence(sourceWords, sourceRel);

        int sourceIndex = 0, targetIndex = 0;

        System.out.println(targetRel);

        for (Diff<Word> wordDiff : diff) {
            switch (wordDiff.operation()) {
                case INSERT:
                    List<GrammaticalRelation> targetWordRelations = targetSentence.getRelationsForWordAt(targetIndex);
                    System.out.println("mistakes were made for the following relations (insert)");
                    System.out.println(targetWordRelations);
                    targetIndex++;
                    break;
                case DELETE:
                    List<GrammaticalRelation> sourceWordRelations = sourceSentence.getRelationsForWordAt(sourceIndex);
                    System.out.println("mistakes were made for the following relations (delete)");
                    System.out.println(sourceWordRelations);
                    sourceIndex++;
                    break;
                case EQUAL:
                    targetIndex++;
                    sourceIndex++;
                    break;
            }
        }
    }

    public static class Elem {
        private final String text;

        public Elem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Elem)) return false;
            Elem elem = (Elem) o;
            return Objects.equals(text, elem.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text);
        }

        @Override
        public String toString() {
            return "Elem{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }
}

