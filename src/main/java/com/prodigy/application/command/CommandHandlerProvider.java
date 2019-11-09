package com.prodigy.application.command;

public interface CommandHandlerProvider {

    <T extends Command> CommandHandler<T> getHandlerForCommand(T command);
}
