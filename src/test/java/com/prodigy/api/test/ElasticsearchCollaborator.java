package com.prodigy.api.test;

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.IndexSettings;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.IOException;
import java.net.ServerSocket;

import static java.lang.ClassLoader.getSystemResourceAsStream;

public class ElasticsearchCollaborator implements Collaborator {

    /**
     * The embedded Elasticseearch executable
     */
    private EmbeddedElastic embeddedElastic;
    /**
     * The URL to connect on
     */
    private String connectionUrl;

    private final int httpPort;
    private final int transportPort;

    public ElasticsearchCollaborator(int transportPort) {
        this.httpPort = 0;
        this.transportPort = transportPort;
    }

    /**
     * Start Elasticseearch running
     *
     * @throws IOException if an error occurs starting Elasticseearch
     */
    public ElasticsearchCollaborator start() throws Exception {
        if (embeddedElastic == null) {

            embeddedElastic = EmbeddedElastic.builder()
                    .withElasticVersion("5.4.0")
//                    .withSetting(PopularProperties.TRANSPORT_TCP_PORT, transportPort)
//                    .withSetting(PopularProperties.HTTP_PORT, httpPort)
                    .withSetting(PopularProperties.TRANSPORT_TCP_PORT, httpPort)
                    .withSetting(PopularProperties.CLUSTER_NAME, "my_cluster")
                    .withPlugin("analysis-stempel")
//                    .withIndex("cars", IndexSettings.builder()
//                            .withType("car", getSystemResourceAsStream("car-mapping.json"))
//                            .build())
                    .build()
                    .start();

            connectionUrl = "http://localhost:" + httpPort;
        }

        return this;
    }

    /**
     * Stop Elasticseearch
     */
    public void stop() {
        if (embeddedElastic != null) {
            embeddedElastic.stop();
        }
    }

    /**
     * Get the URL to use to connect to the database
     *
     * @return the connection URL
     */
    public String getConnectionUrl() {
        return connectionUrl;
    }

    /**
     * Get a free port to listen on
     *
     * @return the port
     * @throws IOException if an error occurs finding a port
     */
    private static int getFreePort() throws IOException {
        ServerSocket s = new ServerSocket(0);
        return s.getLocalPort();
    }

}
