package com.prodigy.service;

public interface ServiceCommand<RESULT, REQUEST extends ServiceRequest>{

    Result<RESULT> execute(REQUEST request);

}
