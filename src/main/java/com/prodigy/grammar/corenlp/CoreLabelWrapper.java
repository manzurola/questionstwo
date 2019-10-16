package com.prodigy.grammar.corenlp;

import com.prodigy.grammar.POS;
import com.prodigy.grammar.Word;
import edu.stanford.nlp.ling.CoreLabel;

public class CoreLabelWrapper implements Word {

    private final CoreLabel coreLabel;

    CoreLabelWrapper(CoreLabel coreLabel) {
        this.coreLabel = coreLabel;
    }

    @Override
    public String value() {
        return coreLabel.value();
    }

    @Override
    public String original() {
        return coreLabel.value().concat(coreLabel.after());
    }

    @Override
    public int index() {
        return coreLabel.index();
    }

    @Override
    public POS posTag() {
        return POS.ofTag(coreLabel.tag());
    }

}
