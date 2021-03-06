package com.prodigy.grammar.corenlp;

// https://nlp.stanford.edu/software/dependencies_manual.pdf
//public class StanfordGrammaticalRelationsParser implements GrammaticalRelationsParser {
//
//    private final DependencyParser parser;
//
//    public StanfordGrammaticalRelationsParser() {
//        String modelPath = DependencyParser.DEFAULT_MODEL;
//        parser = DependencyParser.loadFromModelFile(modelPath);
//    }
//
//    public StanfordGrammaticalRelationsParser(DependencyParser parser) {
//        this.parser = parser;
//    }
//
//    @Override
//    public List<GrammaticalRelation> parse(List<Word> sentence) {
//        List<TaggedWord> tagged = sentence.stream().map(this::asTaggedWord).collect(Collectors.toList());
//
//        GrammaticalStructure sourceGrammar = parser.predict(tagged);
//        return sourceGrammar.typedDependencies().stream()
//                .map(this::toGrammaticalRelation)
//                .collect(Collectors.toList());
//    }
//
//    private TaggedWord asTaggedWord(Word word) {
//        return new TaggedWord(word.value(), word.pos() != null ? word.pos().name() : null);
//    }
//
//    private GrammaticalRelation toGrammaticalRelation(TypedDependency dependency) {
//        edu.stanford.nlp.ling.IndexedWord gov = dependency.gov();
//        edu.stanford.nlp.ling.IndexedWord dep = dependency.dep();
//        return GrammaticalRelation.builder()
//                .withName(dependency.reln().getShortName())
//                .withGovernor(new IndexedWord(
//                        gov.index(),
//                        new Word(gov.word(), POS.ofValue(gov.tag()).orElse(null), null) // TODO fix null
//                ))
//                .withDependant(new IndexedWord(
//                        dep.index(),
//                        new Word(dep.word(), POS.ofValue(dep.tag()).orElse(null), null)
//                ))
//                .build();
//    }
//}
