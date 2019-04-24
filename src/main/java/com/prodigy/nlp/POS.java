package com.prodigy.nlp;

import java.util.Optional;

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
