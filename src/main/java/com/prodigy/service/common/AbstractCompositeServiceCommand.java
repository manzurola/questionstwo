package com.prodigy.service.common;

import com.prodigy.service.ServiceRequest;

public class AbstractCompositeServiceCommand<RESULT, REQUEST extends ServiceRequest> extends AbstractServiceCommand<RESULT, REQUEST> {

    @Override
    protected RESULT doExecute(REQUEST request) throws Exception {
        return null;
    }
}