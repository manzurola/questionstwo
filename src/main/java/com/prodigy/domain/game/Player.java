package com.prodigy.domain.game;

import com.prodigy.common.Id;
import com.prodigy.domain.users.User;

public class Player {

    private final Id<User> userId;

    public Player(Id<User> userId) {
        this.userId = userId;
    }

    public Id<User> getUserId() {
        return userId;
    }
}
