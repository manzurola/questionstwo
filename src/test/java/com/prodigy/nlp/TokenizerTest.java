package com.prodigy.nlp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TokenizerTest {

    @Test
    public void tokenize() {
        Tokenizer tokenizer = new StanfordNLPTokenizer();
        List<Sentence> actual = tokenizer.tokenize("A dog is cute.");
        List<Sentence> expected = Arrays.asList(new Sentence(
                Arrays.asList(new Word("A"), new Word("dog"), new Word("is"), new Word("cute"), new Word("."))
        ));

        Assert.assertEquals(expected, actual);
    }
}
