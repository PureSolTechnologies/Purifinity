package com.puresoltechnologies.toolshed.server;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.puresoltechnologies.toolshed.server.config.ToolShedServerConfiguration;
import com.puresoltechnologies.toolshed.server.filters.CORSFilter;
import com.puresoltechnologies.toolshed.server.metrics.Metrics;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ToolShedServer extends Application<ToolShedServerConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(ToolShedServer.class);

    @Override
    public String getName() {
	return ToolShedServer.class.getSimpleName();
    }

    @Override
    public void initialize(Bootstrap<ToolShedServerConfiguration> bootstrap) {
	File resourceDirectory = new File("/home/ludwig/git/FamilityServer/ui/src/main/web");
	if ((!resourceDirectory.exists()) || (!resourceDirectory.isDirectory())) {
	    throw new IllegalStateException("Resource path '" + resourceDirectory + "' was not found.");
	}
	bootstrap.addBundle(new AssetsBundle(resourceDirectory.getPath(), "", "index.html"));
    }

    @Override
    public void run(ToolShedServerConfiguration configuration, Environment environment) throws Exception {
	MetricRegistry metrics = environment.metrics();
	Metrics.initialize(metrics);

	HealthCheckRegistry healthChecks = environment.healthChecks();
	// healthChecks.register("database", new DatabaseHealthCheck());

	JerseyEnvironment jersey = environment.jersey();
	jersey.setUrlPattern("/rest");
	jersey.register(new CORSFilter());
    }

    @Override
    protected void onFatalError() {
	logger.error("SEVERE ISSUE OCCURED. APPLICATION IS SHUTTING DOWN.");
	super.onFatalError();
    }

    public static void main(String[] args) {
	try {
	    ToolShedServer application = new ToolShedServer();
	    application.run(args);
	} catch (Throwable e) {
	    logger.error("SEVERE ISSUE OCCURED. APPLICATION IS SHUTTING DOWN.", e);
	    System.exit(1);
	}

    }

}
