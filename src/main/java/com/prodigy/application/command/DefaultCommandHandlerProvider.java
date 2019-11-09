package com.prodigy.application.command;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultCommandHandlerProvider implements CommandHandlerProvider {

    private final Map<Class, CommandHandler> handlers;

    private DefaultCommandHandlerProvider(Builder builder) {
        this.handlers = ImmutableMap.<Class, CommandHandler>builder().putAll(builder.handlers).build();
    }

    @Override
    public <T extends Command> CommandHandler<T> getHandlerForCommand(T command) {
        return (CommandHandler<T>) handlers.get(command);
    }

    public static class Builder {
        private final Map<Class, CommandHandler> handlers = new ConcurrentHashMap<>();

        public <T extends Command> Builder register(Class<T> commandType, CommandHandler<T> handler) {
            handlers.put(commandType, handler);
            return this;
        }

        public DefaultCommandHandlerProvider build() {
            return new DefaultCommandHandlerProvider(this);
        }

    }

}
