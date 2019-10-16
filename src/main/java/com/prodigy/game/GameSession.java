package com.prodigy.game;

import java.util.List;
import java.util.Objects;

public class GameSession {

    public enum State {
        PENDING,
        RUNNING,
        PAUSED,
        ENDED
    }

    private final List<Player> players;
    private final List<GameEvent> messages;
    private final State state;

    public GameSession(List<Player> players, List<GameEvent> messages, State state) {
        this.players = players;
        this.messages = messages;
        this.state = state;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<GameEvent> getMessages() {
        return messages;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameSession)) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(players, that.players) &&
                Objects.equals(messages, that.messages) &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, messages, state);
    }
}
