package com.prodigy.grammar.corenlp;

import com.prodigy.grammar.Sentence;
import com.prodigy.grammar.Word;
import edu.stanford.nlp.pipeline.CoreSentence;

import java.util.List;
import java.util.stream.Collectors;

public class CoreSentenceWrapper implements Sentence {

    private final CoreSentence coreSentence;

    CoreSentenceWrapper(CoreSentence coreSentence) {
        this.coreSentence = coreSentence;
    }

    @Override
    public String text() {
        return coreSentence.text();
    }

    @Override
    public List<Word> words() {
        return coreSentence.tokens().stream().map(CoreLabelWrapper::new).collect(Collectors.toList());
    }
}
