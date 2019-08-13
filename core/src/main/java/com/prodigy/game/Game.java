package com.prodigy.game;

public interface Game {

    Question nextQuestion();

    Review submitAnswer(Answer answer);


}
