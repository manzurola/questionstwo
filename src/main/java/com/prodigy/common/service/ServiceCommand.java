package com.prodigy.common.service;

public interface ServiceCommand<RESULT, REQUEST extends ServiceRequest>{

    Result<RESULT> execute(REQUEST request);

}
