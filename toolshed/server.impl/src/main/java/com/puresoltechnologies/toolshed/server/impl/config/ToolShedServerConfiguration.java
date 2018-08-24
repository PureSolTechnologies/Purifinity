package com.puresoltechnologies.toolshed.server.impl.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;

public class ToolShedServerConfiguration extends Configuration {

    @Valid
    @NotNull
    private final List<URL> upstreamServers = new ArrayList<>();

    public void setUpstreamServers(List<URL> upstreamServers) {
	this.upstreamServers.addAll(upstreamServers);
    }

    public List<URL> getUpstreamServers() {
	return upstreamServers;
    }

}
