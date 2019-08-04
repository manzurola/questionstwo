package com.prodigy.game;

public interface Game {

    Question nextQuestion();

    Review reviewAnswer(Answer answer);


}
