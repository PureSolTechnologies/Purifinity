package com.puresoltechnologies.toolshed.server.impl;

import java.io.File;

import javax.websocket.server.ServerEndpointConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.puresoltechnologies.toolshed.server.impl.aggregator.AggregatorThread;
import com.puresoltechnologies.toolshed.server.impl.config.ToolShedServerConfiguration;
import com.puresoltechnologies.toolshed.server.impl.dashboards.DashboardsImpl;
import com.puresoltechnologies.toolshed.server.impl.filters.CORSFilter;
import com.puresoltechnologies.toolshed.server.impl.kpis.KPIServiceImpl;
import com.puresoltechnologies.toolshed.server.impl.metrics.Metrics;
import com.puresoltechnologies.toolshed.server.impl.nodes.NodeManager;
import com.puresoltechnologies.toolshed.server.impl.nodes.NodeServiceImpl;
import com.puresoltechnologies.toolshed.server.impl.ws.AnnotatedEchoServer;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.websockets.WebsocketBundle;

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
	WebsocketBundle webSockets = new WebsocketBundle(new ServerEndpointConfig[] {});
	webSockets.addEndpoint(AnnotatedEchoServer.class);
	bootstrap.addBundle(webSockets);
    }

    @Override
    public void run(ToolShedServerConfiguration configuration, Environment environment) throws Exception {
	MetricRegistry metrics = environment.metrics();
	Metrics.initialize(metrics);

	Thread aggregatorThread = new Thread(new AggregatorThread(), "ToolShed KPI Aggregator");
	aggregatorThread.setDaemon(true);
	aggregatorThread.start();

	HealthCheckRegistry healthChecks = environment.healthChecks();
	// healthChecks.register("database", new DatabaseHealthCheck());

	JerseyEnvironment jersey = environment.jersey();
	jersey.setUrlPattern("/rest");
	jersey.register(new CORSFilter());
	jersey.register(NodeServiceImpl.class);
	jersey.register(KPIServiceImpl.class);
	jersey.register(DashboardsImpl.class);

	NodeManager.initialize(configuration.getUpstreamServers());
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
