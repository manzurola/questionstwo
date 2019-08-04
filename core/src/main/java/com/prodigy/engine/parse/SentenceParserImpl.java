package com.prodigy.engine.parse;

import com.prodigy.engine.Sentence;
import com.prodigy.engine.Word;
import com.prodigy.engine.nlp.GrammaticalRelation;
import com.prodigy.engine.nlp.GrammaticalRelationsParser;
import com.prodigy.engine.tokenize.TaggingTokenizer;

import java.util.List;

public class SentenceParserImpl implements SentenceParser {

    private final TaggingTokenizer tokenizer;
    private final GrammaticalRelationsParser dependencyParser;

    public SentenceParserImpl(TaggingTokenizer tokenizer, GrammaticalRelationsParser dependencyParser) {
        this.tokenizer = tokenizer;
        this.dependencyParser = dependencyParser;
    }

    @Override
    public Sentence parse(String sentence) {
        List<Word> words = tokenizer.tokenize(sentence);
        List<GrammaticalRelation> dependencies = dependencyParser.parse(words);
        return new Sentence(words, dependencies);
    }
}
