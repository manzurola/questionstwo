package com.prodigy.domain;

public interface Game {

    Question nextQuestion();

    Review submitAnswer(Answer answer);


}
