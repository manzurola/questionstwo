package com.prodigy.api.env;

public interface Collaborator {

    <T extends Collaborator> T start() throws Exception;

    void stop();
}
