package com.prodigy.api.test.common.service;

public interface Command<RESULT, REQUEST extends ServiceRequest>{

    Result<RESULT> execute(REQUEST request);

}
