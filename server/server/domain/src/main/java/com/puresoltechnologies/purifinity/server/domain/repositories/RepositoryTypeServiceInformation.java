package com.puresoltechnologies.purifinity.server.domain.repositories;

import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceInformation;

/**
 * This class contains all information about an available repository type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RepositoryTypeServiceInformation implements ServiceInformation {

	private static final long serialVersionUID = 1607123633941143239L;

	private final String className;
	private final String name;
	private final String description;
	private final Map<String, Parameter<?>> parameters = new LinkedHashMap<>();
	private final String serviceURLPath;
	private final String configurationURLPath;
	private final String projectURLPath;
	private final String runURLPath;

	public RepositoryTypeServiceInformation(
			@JsonProperty("className") String className,
			@JsonProperty("name") String name,
			@JsonProperty("description") String description,
			@JsonProperty("parameters") Map<String, Parameter<?>> parameters,
			@JsonProperty("serviceURLPath") String serviceURLPath,
			@JsonProperty("configurationURLPath") String configurationURLPath,
			@JsonProperty("projectURLPath") String projectURLPath,
			@JsonProperty("runURLPath") String runURLPath) {
		super();
		this.className = className;
		this.name = name;
		this.description = description;
		this.parameters.putAll(parameters);
		this.serviceURLPath = serviceURLPath;
		this.configurationURLPath = configurationURLPath;
		this.projectURLPath = projectURLPath;
		this.runURLPath = runURLPath;
	}

	public String getClassName() {
		return className;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Parameter<?>> getParameters() {
		return parameters;
	}

	@Override
	public String getServiceURLPath() {
		return serviceURLPath;
	}

	@Override
	public String getConfigurationURLPath() {
		return configurationURLPath;
	}

	@Override
	public String getProjectURLPath() {
		return projectURLPath;
	}

	@Override
	public String getRunURLPath() {
		return runURLPath;
	}
}
