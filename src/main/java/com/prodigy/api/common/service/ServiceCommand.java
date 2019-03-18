package com.prodigy.api.common.service;

public interface ServiceCommand<RESULT, REQUEST extends ServiceRequest>{

    ServiceResult<RESULT> execute(REQUEST request);

}
