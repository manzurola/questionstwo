package com.prodigy.api.game;

import com.prodigy.api.common.Id;
import com.prodigy.api.users.User;

public class Player {

    private final Id<User> userId;

    public Player(Id<User> userId) {
        this.userId = userId;
    }

    public Id<User> getUserId() {
        return userId;
    }
}
