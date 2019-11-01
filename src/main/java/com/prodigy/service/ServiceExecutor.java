package com.prodigy.service;

public interface ServiceExecutor {

    <E, R extends ServiceRequest, T extends ServiceCommand<E, R>> Result<E> execute(Class<T> commandClass, R request);
}
