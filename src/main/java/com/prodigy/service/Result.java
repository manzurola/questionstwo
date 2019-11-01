package com.prodigy.service;

public interface Result<T> {

    boolean isOk();

    T getData();

    Throwable exception();

    static <RESULT> Result<RESULT> ok(RESULT payload) {
        return new ServiceResultOk<>(payload);
    }

    static <RESULT> Result<RESULT> error(Throwable exception) {
        return new ServiceResultError<>(exception);
    }


}

class ServiceResultOk<T> implements Result<T> {

    private final T payload;

    ServiceResultOk(T payload) {
        this.payload = payload;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public T getData() {
        return payload;
    }

    @Override
    public Throwable exception() {
        return null;
    }

    @Override
    public String toString() {
        return "ServiceResultOk{" +
                "getData=" + payload +
                ", ok=" + isOk() +
                ", exception=" + exception() +
                '}';
    }
}

class ServiceResultError<T> implements Result<T> {

    private Throwable exception;

    ServiceResultError(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public T getData() {
        throw new RuntimeException(exception);
    }

    @Override
    public Throwable exception() {
        return exception;
    }

    @Override
    public String toString() {
        return "ServiceResultError{" +
                "exception=" + exception +
                ", ok=" + isOk() +
                ", getData=" + getData() +
                '}';
    }
}
