package com.prodigy.api.test.env;

public interface Collaborator {

    <T extends Collaborator> T start() throws Exception;

    void stop();
}
