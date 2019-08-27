package com.prodigy.domain.common.service;

public interface ServiceExecutor {

    <E, R extends ServiceRequest, T extends Command<E, R>> Result<E> execute(Class<T> commandClass, R request);
}
