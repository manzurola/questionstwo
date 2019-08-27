package com.prodigy.domain;

import java.util.Objects;

public class User {

    private final Id<User> id;
    private final String email;

    private User(Id<User> id, String email) {
        this.id = id;
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Id<User> getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    public static class Builder {
        private Id<User> id = Id.next();
        private String email;

        public Builder setId(Id<User> id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(id, email);
        }
    }
}
