package com.prodigy.api.common.service;

public interface CommandFactory {

    <E, R extends ServiceRequest, T extends Command<E, R>> Command<E,R> create(Class<T> clazz);
}
