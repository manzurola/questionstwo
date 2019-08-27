package com.prodigy.domain;

public class PathToSolution {

    public static class ReviewStep {

        public enum Result {
            EQUAL,
            DELETE,
            INSERT,
            REPLACE
        }

        private final com.prodigy.domain.ReviewStep.Result result;
        private final String value;

        public ReviewStep(com.prodigy.domain.ReviewStep.Result result, String value) {
            this.result = result;
            this.value = value;
        }

        public com.prodigy.domain.ReviewStep.Result getResult() {
            return result;
        }

        public String getValue() {
            return value;
        }
    }
}
