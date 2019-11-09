package com.prodigy.domain.questions;

import com.prodigy.domain.diff.SentenceDiff;

public interface SentenceDiffFeedbackProvider {

    String getFeedback(SentenceDiff diff);
}
