package com.prodigy.domain.common.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public abstract class AbstractCommand<RESULT, REQUEST extends ServiceRequest>
        implements Command<RESULT, REQUEST> {

    protected Validator validator;

    public AbstractCommand() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public final Result<RESULT> execute(REQUEST request) {
        Set<ConstraintViolation<REQUEST>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            return Result.error(new CommandValidationException(null));
        }

        try {
            return Result.ok(doExecute(request));
        } catch (Exception e) {
            return Result.error(new CommandExecutionExecption(e));
        }
    }

    protected abstract RESULT doExecute(REQUEST request) throws Exception;
}