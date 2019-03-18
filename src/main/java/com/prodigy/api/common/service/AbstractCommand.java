package com.prodigy.api.common.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public abstract class AbstractCommand<RESULT, REQUEST extends ServiceRequest>
        implements ServiceCommand<RESULT, REQUEST>{

    protected Validator validator;

    public AbstractCommand() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public final ServiceResult<RESULT> execute(REQUEST request) {
        Set<ConstraintViolation<REQUEST>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            return ServiceResult.error(new CommandValidationException(null));
        }

        try {
            return ServiceResult.ok(doExecute(request));
        } catch (Exception e) {
            return ServiceResult.error(new CommandExecutionExecption(e));
        }
    }

    protected abstract RESULT doExecute(REQUEST request) throws Exception;
}