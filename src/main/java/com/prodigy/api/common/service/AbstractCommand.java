package com.prodigy.api.common.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public abstract class AbstractCommand<RESULT, REQUEST extends ServiceRequest>
        implements ServiceCommand<RESULT, REQUEST>{

    protected Validator validator;

    @Override
    public final RESULT execute(REQUEST request) {
        Set<ConstraintViolation<REQUEST>> constraintViolations = validator.validate(request);
        if (constraintViolations.isEmpty()) {
            return doExecute(request);
        } else {
            throw new CommandValidationException(null);
        }
    }

    public abstract RESULT doExecute(REQUEST request);
}