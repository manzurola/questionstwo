package com.prodigy.api.common.service;

import java.util.List;

public class AbstractCompositeCommand<RESULT, REQUEST extends ServiceRequest> extends AbstractCommand<RESULT, REQUEST> {

    @Override
    protected RESULT doExecute(REQUEST request) throws Exception {
        return null;
    }
}
