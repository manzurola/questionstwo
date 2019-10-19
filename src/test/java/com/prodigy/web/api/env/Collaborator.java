package com.prodigy.web.api.env;

public interface Collaborator {

    <T extends Collaborator> T start() throws Exception;

    void stop();
}
