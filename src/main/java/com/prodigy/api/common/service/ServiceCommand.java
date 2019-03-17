package com.prodigy.api.common.service;

public interface ServiceCommand<RESULT, REQUEST extends ServiceRequest>{

    RESULT execute(REQUEST request);

}
