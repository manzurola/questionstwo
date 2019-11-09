package com.prodigy.domain.nlp;

import java.util.HashMap;
import java.util.Map;

// explanations on the different parts of speech can be found here - https://web.stanford.edu/class/cs124/lec/postagging.pdf
public enum POS {
    CC("CC"),
    CD("CD"),
    DT("DT"),
    EX("EX"),
    FW("FW"),
    IN("IN"),
    JJ("JJ"),
    JJR("JJR"),
    JJS("JJS"),
    LS("LS"),
    MD("MD"),
    NN("NN"),
    NNS("NNS"),
    NNP("NNP"),
    NNPS("NNPS"),
    PDT("PDT"),
    POS("POS"),
    PRP("PRP"),
    PRP$("PRPS"),
    RB("RB"),
    RBR("RBR"),
    RBS("RBS"),
    RP("RP"),
    SYM("SYM"),
    TO("TO"),
    UH("UH"),
    VB("VB"),
    VBD("VBD"),
    VBG("VBG"),
    VBN("VBN"),
    VBP("VBP"),
    VBZ("VBZ"),
    WDT("WDT"),
    WP("WP"),
    WP$("WPS"),
    WRB("WRB"),
    DOLLAR_SIGN("$"),
    POUND_SIGN("#"),
    L_QUOTE("\'"),
    R_QUOTE("\""),
    L_PAREN("("),
    R_PAREN(")"),
    COMMA(","),
    SENT_FIN("."),
    SENT_MID(":");

    private final static Map<String, POS> map;
    static {
        map = new HashMap<>();
        for (POS pos : com.prodigy.domain.nlp.POS.values()) {
            map.put(pos.tag, pos);
        }
    }

    private final String tag;

    POS(String tag) {
        this.tag = tag;
    }

    public static POS ofTag(String tag) {
        return map.get(tag);
    }
}
