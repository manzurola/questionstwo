package com.prodigy.webapp.api.env;

import com.prodigy.database.impl.ElasticsearchQuestionRepository;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class EmbeddedElasticsearch implements Collaborator {

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
    private final String clusterName;

    public EmbeddedElasticsearch(int transportPort, String clusterName) {
        this.httpPort = 0;
        this.transportPort = transportPort;
        this.clusterName = clusterName;
    }

    /**
     * Start Elasticseearch running
     *
     * @throws IOException if an error occurs starting Elasticseearch
     */
    public EmbeddedElasticsearch start() throws Exception {
        if (embeddedElastic == null) {

            embeddedElastic = EmbeddedElastic.builder()
                    .withElasticVersion("5.0.0")
//                    .withSetting(PopularProperties.HTTP_PORT, httpPort)
                    .withSetting(PopularProperties.TRANSPORT_TCP_PORT, transportPort)
                    .withSetting(PopularProperties.CLUSTER_NAME, clusterName)
                    .withInstallationDirectory(new File(System.getProperty("user.dir") + "/temp/elasticsearch"))
//                    .withPlugin("analysis-stempel")
                    .withIndex(ElasticsearchQuestionRepository.index)
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
