package com.prodigy.service;

public class AbstractCompositeServiceCommand<RESULT, REQUEST extends ServiceRequest> extends AbstractServiceCommand<RESULT, REQUEST> {

    @Override
    protected RESULT doExecute(REQUEST request) throws Exception {
        return null;
    }
}
