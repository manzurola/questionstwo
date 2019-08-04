package com.prodigy.engine.tokenize;

import com.prodigy.engine.POS;
import com.prodigy.engine.Word;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TaggingTokenizer implements SentenceTokenizer {

    private final MaxentTagger tagger;

    public TaggingTokenizer() {
        tagger = new MaxentTagger("edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger");
    }

    public TaggingTokenizer(MaxentTagger tagger) {
        this.tagger = tagger;
    }

    @Override
    public List<Word> tokenize(String sentence) {
        Iterator<List<HasWord>> lines = new DocumentPreprocessor(new StringReader(sentence)).iterator();
        if (!lines.hasNext()) {
            return new ArrayList<>();
        }
        List<edu.stanford.nlp.ling.TaggedWord> sourceTagged = tagger.tagSentence(lines.next());
        return sourceTagged.stream()
                .map(t -> new Word(
                                t.word(),
                                POS.ofValue(t.tag()).orElse(null)
                        )
                ).collect(Collectors.toList());
    }
}
