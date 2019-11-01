package com.prodigy.domain.core;

import com.prodigy.diff.*;
import com.prodigy.grammar.corenlp.CoreSentenceWrapperFactory;
import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.SentenceFactory;
import com.prodigy.questions.domain.Question;
import com.prodigy.utils.QuestionTestData;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.process.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

public class TestStuff {

    @Test
    public void sentenceFactoryTest() {
        SentenceFactory factory = new CoreSentenceWrapperFactory();
        Sentence sentence = factory.getSentence("This dress is very old; I can't wear it any more.");
        System.out.println(sentence);
    }

    private static class Wrapper {
        CoreLabel coreLabel;

        public Wrapper(CoreLabel coreLabel) {
            this.coreLabel = coreLabel;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wrapper wrapper = (Wrapper) o;
            return coreLabel.word().equals(wrapper.coreLabel.word());
        }

        @Override
        public int hashCode() {
            return Objects.hash(coreLabel.word());
        }

        @Override
        public String toString() {
            return "Wrapper{" +
                    "word=" + coreLabel.word() +
                    '}';
        }
    }

    @Test
    public void printMistakes() {
        QuestionTestData data = new QuestionTestData();
        List<Question> questions = data.questions();

        for (Question question : questions) {
            SentenceDiffCheck diffChecker = new SentenceDiffCheckImpl(new DMPListDiffCheck());

        }
    }

    @Test
    public void assemble() {
        String target = "Doesn't he love his wife?";
        String source = "Doesn't he loves his wife?";

        List<CoreLabel> targetWords = new PTBTokenizer<>(
                new StringReader(target),
                new CoreLabelTokenFactory(true),
                "invertible"
        ).tokenize();

        List<CoreLabel> sourceWords = new PTBTokenizer<>(
                new StringReader(source),
                new CoreLabelTokenFactory(true),
                "invertible"
        ).tokenize();

        ListDiffCheck diffCalculator = new ListDiffCheck();
        List<Diff<Wrapper>> diff = diffCalculator.diffSourceAndTarget(
                sourceWords.stream().map(Wrapper::new).collect(Collectors.toList()),
                targetWords.stream().map(Wrapper::new).collect(Collectors.toList())
        );
        System.out.println(diff);

//        StringBuilder builder = new StringBuilder();
//        for (Diff<Wrapper> wordWrapperDiff : diff) {
//            String op = wordWrapperDiff.operation().equals(Diff.Operation.DELETE) ? "(-)" : (wordWrapperDiff.operation().equals(Diff.Operation.INSERT) ? "(+)" : "");
//            builder.append(String.format("%s%s ", op, wordWrapperDiff.object().getWord().word()));
//        }
//        return builder.toString();
    }

    // https://nlp.stanford.edu/nlp/javadoc/javanlp/edu/stanford/nlp/process/PTBTokenizer.html
    @Test
    public void testTokenize() {
        String text = "Doesn't      he love his wife organisation?".trim().replaceAll(" +", " ");
        List<edu.stanford.nlp.ling.Word> words = WhitespaceTokenizer.factory().getTokenizer(new StringReader(text)).tokenize();
        System.out.println(words);

        List<edu.stanford.nlp.ling.Word> ptb = PTBTokenizer.factory().getTokenizer(new StringReader(text)).tokenize();
        System.out.println(ptb);

        List<CoreLabel> coreLabels = new PTBTokenizer<>(new StringReader(text), new CoreLabelTokenFactory(true), "invertible").tokenize();
        for (CoreLabel coreLabel : coreLabels) {
            System.out.println(String.format("word: '%s', ner: '%s', tag: '%s', index: %d, begin: %d, end: %d, before: '%s', after: '%s'", coreLabel.word(), coreLabel.ner(), coreLabel.tag(), coreLabel.index(), coreLabel.beginPosition(), coreLabel.endPosition(), coreLabel.before(), coreLabel.after()));
        }

        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = tagger();
        List<TaggedWord> taggedWords = tagger.tagSentence(coreLabels);
        System.out.println(taggedWords);
    }

    private MaxentTagger tagger() {
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
        MaxentTagger tagger = new MaxentTagger(taggerPath);
        return tagger;
    }

    @Test
    public void testLargeDiff() {
        List<String> target = new ArrayList<>();
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            target.add(String.valueOf(Math.random()));
        }

        List<String> source = new ArrayList<>();

        ListDiffCheck diffCalculator = new ListDiffCheck();
        List<Diff<String>> actual = diffCalculator.diffSourceAndTarget(source, target);
        System.out.println(actual);
    }

    @Test
    public void testDiff() {


        List<Elem> target = Arrays.asList(new Elem("hello"), new Elem("my"), new Elem("name"), new Elem("is"), new Elem("guy"));
        List<Elem> source = Arrays.asList(new Elem("hello"), new Elem("name"), new Elem("is"), new Elem("guy"));

        List<Diff<Elem>> expected = Arrays.asList(
                new Diff<>(Operation.EQUAL, new Elem("hello")),
                new Diff<>(Operation.INSERT, new Elem("my")),
                new Diff<>(Operation.EQUAL, new Elem("name")),
                new Diff<>(Operation.EQUAL, new Elem("is")),
                new Diff<>(Operation.EQUAL, new Elem("guy"))
        );

        ListDiffCheck hashCodeDiffChecker = new ListDiffCheck();
        List<Diff<Elem>> actual = hashCodeDiffChecker.diffSourceAndTarget(source, target);

        Assert.assertEquals(expected, actual);
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

