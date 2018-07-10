package com.puresoltechnologies.toolshed.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import com.puresoltechnologies.toolshed.client.ToolShedCient;
import com.puresoltechnologies.toolshed.server.impl.ToolShedServer;
import com.puresoltechnologies.toolshed.server.impl.config.ToolShedServerConfiguration;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
public abstract class AbstractToolShedServerTest {

    private static String configurationFile = ResourceHelpers.resourceFilePath("server.yml");
    /**
     * @see https://github.com/dropwizard/dropwizard/blob/master/docs/source/manual/testing.rst
     */
    public static final DropwizardAppExtension<ToolShedServerConfiguration> dropwizard = new DropwizardAppExtension<>(
	    ToolShedServer.class, configurationFile);

    private static ToolShedCient client;

    @BeforeAll
    public static void initializeClient() throws URISyntaxException {
	client = new ToolShedCient(new URI("http://localhost:8080"));
    }

    @AfterAll
    public static void destroyClient() throws IOException {
	client.close();
    }

    public static ToolShedCient getClient() {
	return client;
    }
}
