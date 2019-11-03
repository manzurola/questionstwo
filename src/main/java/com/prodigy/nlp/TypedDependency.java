package com.prodigy.nlp;

import java.util.Optional;

public enum TypedDependency {

    ACOMP(0, "adjectival complement"),
    ADVCL(1, "adverbial clause modifier"),
    ADVMOD(2, "adverb modifier"),
    AGENT(3, "agent"),
    APPOS(4, "appositional modifier"),
    AUX(5, "auxiliary"),
    AUXPASS(6, "passive auxiliary"),
    CC(7, "coordination"),
    CCOMP(8, "clausal complement"),
    CONJ(9, "conjunct"),
    COP(10, "copula"),
    CSUBJ(11, " clausal subject"),
    CSUBJPASS(12, "clausal passive subject"),
    DEP(13, "dependent"),
    DET(14, "determiner"),
    DISCOURSE(15, "discourse element"),
    DOBJ(16, "direct object"),
    EXPL(17, "expletive"),
    GOESWITH(18, "goes with"),
    IOBJ(19, "indirect object"),
    MARK(20, "marker"),
    MWE(21, "multi-word expression"),
    NEG(22, "negation modifier"),
    NN(23, "noun compound modifier"),
    NPADVMOD(24, "noun phrase as adverbial modifier"),
    NSUBJ(25, "nominal subject"),
    NSUBJPASS(26, "passive nominal subject"),
    NUM(27, "numeric modifier"),
    NUMBER(28, "element of compound number"),
    PARATAXIS(29, "parataxis"),
    PCOMP(30, "prepositional complement"),
    POBJ(31, "object of a preposition"),
    POSS(32, "possession modifier"),
    POSSESSIVE(33, "possessive modifier"),
    PRECONJ(34, "preconjunct"),
    PREDET(35, "predeterminer"),
    PREP(36, "prepositional modifier"),
    PREPC(37, "prepositional clausal modifier"),
    PRT(38, "phrasal verb particle"),
    PUNCT(39, "punctuation"),
    QUANTMOD(40, "quantifier phrase modifier"),
    RCMOD(41, "relative clause modifier"),
    REF(42, "referent"),
    ROOT(43, "root"),
    TMOD(44, "temporal modifier"),
    VMOD(45, "reduced non-finite verbal modifier"),
    XCOMP(46, "open clausal complement"),
    XSUBJ(47, "controlling subject");

    private final int index;
    private final String longName;

    TypedDependency(int index, String longName) {
        this.index = index;
        this.longName = longName;
    }

    public int index() {
        return index;
    }

    public String longName() {
        return longName;
    }

    public static Optional<TypedDependency> ofValue(String value) {
        try {
            return Optional.of(Enum.valueOf(TypedDependency.class, value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
