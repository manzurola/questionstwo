package com.prodigy.game;

import com.prodigy.answers.domain.Answer;
import com.prodigy.common.data.Id;
import com.prodigy.questions.domain.Question;
import com.prodigy.review.Review;
import com.prodigy.users.User;

public abstract class GameEvent<T> {

    protected final int id;
    protected final Id<GameSession> sessionId;
    protected final T data;

    public GameEvent(int id, Id<GameSession> sessionId, T data) {
        this.id = id;
        this.sessionId = sessionId;
        this.data = data;
    }

    public class UserJoined extends GameEvent<Id<User>> {

        public UserJoined(int id, Id<GameSession> sessionId, Id<User> data) {
            super(id, sessionId, data);
        }
    }

    public class UserQuit extends GameEvent<Id<User>> {

        public UserQuit(int id, Id<GameSession> sessionId, Id<User> data) {
            super(id, sessionId, data);
        }
    }

    public class GameStarted extends GameEvent<Void> {

        public GameStarted(int id, Id<GameSession> sessionId, Void data) {
            super(id, sessionId, data);
        }
    }

    public class GameEnded extends GameEvent<Void> {
        public GameEnded(int id, Id<GameSession> sessionId, Void data) {
            super(id, sessionId, data);
        }
    }

    public class NewQuestion extends GameEvent<Question> {

        public NewQuestion(int id, Id<GameSession> sessionId, Question data) {
            super(id, sessionId, data);
        }
    }

    public class AnswerSubmitted extends GameEvent<Answer> {

        public AnswerSubmitted(int id, Id<GameSession> sessionId, Answer data) {
            super(id, sessionId, data);
        }
    }

    public class QuestionCompleted extends GameEvent<Review> {

        public QuestionCompleted(int id, Id<GameSession> sessionId, Review data) {
            super(id, sessionId, data);
        }
    }
}

