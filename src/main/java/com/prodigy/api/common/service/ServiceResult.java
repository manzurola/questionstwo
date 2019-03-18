package com.prodigy.api.common.service;

public interface ServiceResult<T> {

    boolean isOk();

    T payload();

    Throwable exception();

    static <RESULT> ServiceResult<RESULT> ok(RESULT payload) {
        return new ServiceResultOk<>(payload);
    }

    static <RESULT> ServiceResult<RESULT> error(Throwable exception) {
        return new ServiceResultError<>(exception);
    }
}

class ServiceResultOk<T> implements ServiceResult<T> {

    private final T payload;

    ServiceResultOk(T payload) {
        this.payload = payload;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public T payload() {
        return payload;
    }

    @Override
    public Throwable exception() {
        return null;
    }
}

class ServiceResultError<T> implements ServiceResult<T>{

    private Throwable exception;

    ServiceResultError(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public T payload() {
        return null;
    }

    @Override
    public Throwable exception() {
        return exception;
    }
}
