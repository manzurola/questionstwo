package com.prodigy.domain.nlp;

import java.util.List;

public interface SentenceFactory {

    Sentence fromString(String value);

    default Sentence fromTokens(List<Token> tokens) {
        StringBuilder builder = new StringBuilder();
        for (Token token : tokens) {
            builder.append(token.original());
        }
        return fromString(builder.toString());
    }
}
