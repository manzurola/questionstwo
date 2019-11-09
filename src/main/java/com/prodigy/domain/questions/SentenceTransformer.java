package com.prodigy.domain.questions;

import com.prodigy.domain.nlp.Sentence;

public interface SentenceTransformer {

    SentenceTransformation transform(Sentence source, Sentence target);
}
