package com.prodigy.api.test;

public interface Collaborator {

    <T extends Collaborator> T start() throws Exception;

    void stop();
}
