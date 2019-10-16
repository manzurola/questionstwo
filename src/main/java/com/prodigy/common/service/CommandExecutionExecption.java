package com.prodigy.common.service;

public class CommandExecutionExecption extends RuntimeException {

    public CommandExecutionExecption(String message) {
        super(message);
    }

    public CommandExecutionExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecutionExecption(Throwable cause) {
        super(cause);
    }
}
