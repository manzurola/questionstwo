package com.prodigy.domain.questions;

import com.prodigy.domain.nlp.Sentence;

public interface TransformationOperation {
    String operation();
    Sentence before();
    Sentence after();
}
