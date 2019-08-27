package com.prodigy.domain.common.service;

public class ServiceExecutorImpl implements ServiceExecutor {

    private final CommandFactory commandFactory;

    public ServiceExecutorImpl(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public <E, R extends ServiceRequest, T extends Command<E, R>> Result<E> execute(Class<T> commandClass, R request) {
        Command<E, R> command = commandFactory.create(commandClass);
        return command.execute(request);
    }
}
