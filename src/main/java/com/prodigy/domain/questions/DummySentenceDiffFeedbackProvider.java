package com.prodigy.domain.questions;

import com.prodigy.domain.diff.SentenceDiff;

public class DummySentenceDiffFeedbackProvider implements SentenceDiffFeedbackProvider {
    @Override
    public String getFeedback(SentenceDiff diff) {
        return "Hello Feedback!";
    }
}
