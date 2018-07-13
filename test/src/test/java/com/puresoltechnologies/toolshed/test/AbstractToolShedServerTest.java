package com.puresoltechnologies.toolshed.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.toolshed.client.ToolShedCient;
import com.puresoltechnologies.toolshed.server.impl.ToolShedServer;
import com.puresoltechnologies.toolshed.server.impl.config.ToolShedServerConfiguration;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public abstract class AbstractToolShedServerTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractToolShedServerTest.class);
    private static final String configurationFile = ResourceHelpers.resourceFilePath("server.yml");
    /**
     * @see https://github.com/dropwizard/dropwizard/blob/master/docs/source/manual/testing.rst
     */
    public static final DropwizardAppExtension<ToolShedServerConfiguration> dropwizard = new DropwizardAppExtension<>(
	    ToolShedServer.class, configurationFile);

    private static ToolShedCient client;

    @BeforeAll
    public static void initializeClient() throws URISyntaxException {
	logger.info("Initializing test client...");
	client = new ToolShedCient(new URI("http://localhost:8080"));
	logger.info("Test client initialized.");
    }

    @AfterAll
    public static void destroyClient() throws IOException {
	if (client != null) {
	    logger.info("Closing test client...");
	    client.close();
	    client = null;
	    logger.info("Test client closed.");
	}
    }

    public static ToolShedCient getClient() {
	return client;
    }
}
