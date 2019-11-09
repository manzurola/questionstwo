package com.prodigy.domain.questions;

import com.prodigy.domain.nlp.Sentence;

public interface SentenceTransformation {

    SentenceTransform transform(Sentence source, Sentence target);
}
