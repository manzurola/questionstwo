package com.prodigy.core;

import com.prodigy.core.diff.DMPDiffCalculator;
import com.prodigy.core.diff.Diff;
import com.prodigy.core.nlp.GrammaticalRelation;
import com.prodigy.core.nlp.GrammaticalRelationsParser;
import com.prodigy.core.nlp.StanfordGrammaticalRelationsParser;
import com.prodigy.core.tokenize.TaggingTokenizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestStuff {

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

        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();
        List<Diff<Elem>> actual = diffCalculator.getDiff(source, target);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void grammarRelations() {
        String sentence = "hello my name is Guy and I am happy.";
        TaggingTokenizer tokenizer = new TaggingTokenizer();
        List<Word> words = tokenizer.tokenize(sentence);
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        List<GrammaticalRelation> relations = parser.parse(words);
        System.out.println(relations);
    }

    @Test
    public void testRelationalDiffEquals() {
        TaggingTokenizer tokenizer = new TaggingTokenizer();
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();

        String target = "hello my name is Guy and I am happy.";
        List<Word> targetWords = tokenizer.tokenize(target);
        List<GrammaticalRelation> targetRel = parser.parse(targetWords);
        System.out.println(targetRel);

        String source = "hello my name is Guy and I am happy.";
        tokenizer = new TaggingTokenizer();
        List<Word> sourceWords = tokenizer.tokenize(source);
        List<GrammaticalRelation> sourceRel = parser.parse(sourceWords);
        System.out.println(sourceRel);

        List<Diff<GrammaticalRelation>> diff = diffCalculator.getDiff(sourceRel, targetRel);
        System.out.println(diff);
    }

    @Test
    public void testRelationalDiff() {
        TaggingTokenizer tokenizer = new TaggingTokenizer();
        GrammaticalRelationsParser parser = new StanfordGrammaticalRelationsParser();
        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();

        String target = "hello my name is Guy and I am happy.";
        List<Word> targetWords = tokenizer.tokenize(target);
        List<GrammaticalRelation> targetRel = parser.parse(targetWords);
        System.out.println(targetWords);

        String source = "hello my is name is Guy and I happy.";
        tokenizer = new TaggingTokenizer();
        List<Word> sourceWords = tokenizer.tokenize(source);
        List<GrammaticalRelation> sourceRel = parser.parse(sourceWords);
        System.out.println(sourceWords);

        List<Diff<Word>> diff = diffCalculator.getDiff(sourceWords, targetWords);

        Sentence targetSentence = new Sentence(targetWords, targetRel);
        Sentence sourceSentence = new Sentence(sourceWords, sourceRel);

        int sourceIndex = 0, targetIndex = 0;

        System.out.println(targetRel);

        for (Diff<Word> wordDiff : diff) {
            switch (wordDiff.getOperation()) {
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

    @Test
    public void name() {
        char c = 65535;
        System.out.println(c == Character.MAX_VALUE);
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

