package com.prodigy.nlp;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.TypedDependency;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

// https://nlp.stanford.edu/software/dependencies_manual.pdf
public class StanfordSentenceParser implements SentenceParser {

    private final MaxentTagger tagger;
    private final DependencyParser parser;

    public StanfordSentenceParser(MaxentTagger tagger, DependencyParser parser) {
        this.tagger = tagger;
        this.parser = parser;
    }

    @Override
    public Sentence parse(String sentence) {
        DocumentPreprocessor doc = new DocumentPreprocessor(new StringReader(sentence));
        Iterator<List<HasWord>> iterator = doc.iterator();
        if (!iterator.hasNext()) {
            return new Sentence(sentence, new ArrayList<>(), new ArrayList<>());
        }
        List<edu.stanford.nlp.ling.TaggedWord> sourceTagged = tagger.tagSentence(iterator.next());

        List<TaggedWord> collected = new ArrayList<>();
        for (int i = 0; i < sourceTagged.size(); i++) {
            edu.stanford.nlp.ling.TaggedWord word = sourceTagged.get(i);new edu.stanford.nlp.ling.TaggedWord(word);
            collected.add(new com.prodigy.nlp.TaggedWord(word.word(), POS.ofValue(word.tag()).orElse(null), i));
        }
        GrammaticalStructure sourceGrammar = parser.predict(sourceTagged);
        List<GrammaticalRelation> relations = sourceGrammar.typedDependencies().stream()
                .map(this::toGrammaticalRelation)
                .collect(Collectors.toList());

        return new Sentence(sentence, collected, relations);
    }

    private GrammaticalRelation toGrammaticalRelation(TypedDependency dependency) {
        edu.stanford.nlp.ling.IndexedWord gov = dependency.gov();
        edu.stanford.nlp.ling.IndexedWord dep = dependency.dep();
        return GrammaticalRelation.builder()
                .withName(dependency.reln().getShortName())
                .withGovernor(new IndexedWord(
                        gov.index(),
                        new TaggedWord(gov.word(), POS.ofValue(gov.tag()).orElse(null), gov.index())
                ))
                .withDependant(new IndexedWord(
                        dep.index(),
                        new TaggedWord(dep.word(), POS.ofValue(dep.tag()).orElse(null), dep.index())
                ))
                .build();
    }
}
