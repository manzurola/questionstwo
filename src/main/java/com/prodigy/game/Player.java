package com.prodigy.game;

import com.prodigy.common.data.Id;
import com.prodigy.users.User;

public class Player {

    private final Id<User> userId;

    public Player(Id<User> userId) {
        this.userId = userId;
    }

    public Id<User> getUserId() {
        return userId;
    }
}
