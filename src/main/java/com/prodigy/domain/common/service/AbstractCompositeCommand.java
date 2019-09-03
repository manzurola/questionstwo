package com.prodigy.domain.common.service;

public class AbstractCompositeCommand<RESULT, REQUEST extends ServiceRequest> extends AbstractCommand<RESULT, REQUEST> {

    @Override
    protected RESULT doExecute(REQUEST request) throws Exception {
        return null;
    }
}
