package com.prodigy.common.service;

public interface CommandFactory {

    <E, R extends ServiceRequest, T extends ServiceCommand<E, R>> ServiceCommand<E,R> create(Class<T> clazz);
}
