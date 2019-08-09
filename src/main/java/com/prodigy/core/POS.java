package com.prodigy.core;

import java.util.Optional;

// explanations on the different parts of speech can be found here - https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html
public enum POS {
    CC,
    CD,
    DT,
    EX,
    FW,
    IN,
    JJ,
    JJR,
    JJS,
    LS,
    MD,
    NN,
    NNS,
    NNP,
    NNPS,
    PDT,
    POS,
    PRP,
    PRP$,
    RB,
    RBR,
    RBS,
    RP,
    SYM,
    TO,
    UH,
    VB,
    VBD,
    VBG,
    VBN,
    VBP,
    VBZ,
    WDT,
    WP,
    WP$,
    WRB,
    PUNC;

    public static Optional<POS> ofValue(String value) {
        try {
            return Optional.of(Enum.valueOf(POS.class, value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
