package com.prodigy.nlp;

import java.util.List;

public interface ContractionResolver {

    List<Word> resolve(Word contracted);
}
