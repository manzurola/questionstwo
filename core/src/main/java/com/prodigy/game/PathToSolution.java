package com.prodigy.game;

public class PathToSolution {

    public static class ReviewStep {

        public enum Result {
            EQUAL,
            DELETE,
            INSERT,
            REPLACE
        }

        private final com.prodigy.game.ReviewStep.Result result;
        private final String value;

        public ReviewStep(com.prodigy.game.ReviewStep.Result result, String value) {
            this.result = result;
            this.value = value;
        }

        public com.prodigy.game.ReviewStep.Result getResult() {
            return result;
        }

        public String getValue() {
            return value;
        }
    }
}
