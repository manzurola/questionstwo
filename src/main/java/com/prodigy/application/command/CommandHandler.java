package com.prodigy.application.command;

public interface CommandHandler<COMMAND extends Command> {

    void handle(COMMAND command);

}
