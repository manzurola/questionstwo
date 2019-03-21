package com.prodigy.api.test.common.service;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CommandValidationException extends RuntimeException {

    public CommandValidationException(Set<ConstraintViolation> violations) {
        super();
    }

}
