package com.prodigy.common.service;

public class ServiceExecutorImpl implements ServiceExecutor {

    private final CommandFactory commandFactory;

    public ServiceExecutorImpl(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public <E, R extends ServiceRequest, T extends ServiceCommand<E, R>> Result<E> execute(Class<T> commandClass, R request) {
        ServiceCommand<E, R> command = commandFactory.create(commandClass);
        return command.execute(request);
    }
}
