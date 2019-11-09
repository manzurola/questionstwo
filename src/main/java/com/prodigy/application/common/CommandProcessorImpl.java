package com.prodigy.application.common;

import com.prodigy.application.command.Command;
import com.prodigy.application.command.CommandHandlerProvider;
import com.prodigy.application.command.CommandProcessor;

public class CommandProcessorImpl implements CommandProcessor {

    private final CommandHandlerProvider handlers;

    public CommandProcessorImpl(CommandHandlerProvider handlers) {
        this.handlers = handlers;
    }

    @Override
    public void process(Command command) {
        handlers.getHandlerForCommand(command).handle(command);
    }
}
