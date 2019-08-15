package com.prodigy.core;

import com.prodigy.core.nlp.PTBSentenceTokenizer;
import com.prodigy.core.nlp.SentenceTokenizer;
import com.prodigy.core.nlp.WhitespaceSentenceTokenizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SentenceTokenizerTest {

    @Test
    public void whitespaceTokenizer() {

        String sentence = "Exercises are not always easy for beginners.";
        List<Word> expected = Arrays.asList("Exercises ", "are ", "not ", "always ", "easy ", "for ", "beginners.")
                .stream()
                .map(s -> new Word(s.trim(), s))
                .collect(Collectors.toList());

        SentenceTokenizer tokenizer = new WhitespaceSentenceTokenizer();
        List<Word> actual = tokenizer.tokenize(sentence);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void ptbTokenizer() {

        String sentence = "Exercises aren't always easy for beginners.";
        List<Word> expected = Arrays.asList("Exercises ", "are", "n't ", "always ", "easy ", "for ", "beginners", ".")
                .stream()
                .map(s -> new Word(s.trim(), s))
                .collect(Collectors.toList());

        SentenceTokenizer tokenizer = new PTBSentenceTokenizer();
        List<Word> actual = tokenizer.tokenize(sentence);

        Assert.assertEquals(expected, actual);

    }
}
