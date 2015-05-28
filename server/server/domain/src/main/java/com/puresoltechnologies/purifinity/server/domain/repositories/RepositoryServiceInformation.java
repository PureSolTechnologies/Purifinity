package com.puresoltechnologies.purifinity.server.domain.repositories;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceInformation;
import com.puresoltechnologies.versioning.Version;

/**
 * This class contains all information about an available repository type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RepositoryServiceInformation implements ServiceInformation {

	private static final long serialVersionUID = 1607123633941143239L;

	private final String id;
	private final String name;
	private final String version;
	private final Version pluginVersion;
	private final String jndiName;
	private final String description;
	private final Map<String, Parameter<?>> parameters = new LinkedHashMap<>();
	private final List<ConfigurationParameter<?>> configurationParameters = new ArrayList<>();
	private final String serviceURLPath;
	private final String configurationURLPath;
	private final String projectURLPath;
	private final String runURLPath;

	public RepositoryServiceInformation() {
		id = null;
		name = null;
		version = null;
		pluginVersion = null;
		jndiName = null;
		description = null;
		serviceURLPath = null;
		configurationURLPath = null;
		projectURLPath = null;
		runURLPath = null;
	}

	@JsonCreator
	public RepositoryServiceInformation(
			@JsonProperty("id") String id,
			@JsonProperty("name") String name,
			@JsonProperty("version") String version,
			@JsonProperty("pluginVersion") Version pluginVersion,
			@JsonProperty("jndiName") String jndiName,
			@JsonProperty("description") String description,
			@JsonProperty("parameters") Map<String, Parameter<?>> parameters,
			@JsonProperty("configurationParameters") List<ConfigurationParameter<?>> configurationParameters,
			@JsonProperty("serviceURLPath") String serviceURLPath,
			@JsonProperty("configurationURLPath") String configurationURLPath,
			@JsonProperty("projectURLPath") String projectURLPath,
			@JsonProperty("runURLPath") String runURLPath) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.pluginVersion = pluginVersion;
		this.jndiName = jndiName;
		this.description = description;
		this.parameters.putAll(parameters);
		this.configurationParameters.addAll(configurationParameters);
		this.serviceURLPath = serviceURLPath;
		this.configurationURLPath = configurationURLPath;
		this.projectURLPath = projectURLPath;
		this.runURLPath = runURLPath;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public Version getPluginVersion() {
		return pluginVersion;
	}

	public String getJndiName() {
		return jndiName;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, Parameter<?>> getParameters() {
		return parameters;
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return configurationParameters;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((configurationURLPath == null) ? 0 : configurationURLPath
						.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((projectURLPath == null) ? 0 : projectURLPath.hashCode());
		result = prime * result
				+ ((runURLPath == null) ? 0 : runURLPath.hashCode());
		result = prime * result
				+ ((serviceURLPath == null) ? 0 : serviceURLPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepositoryServiceInformation other = (RepositoryServiceInformation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (configurationURLPath == null) {
			if (other.configurationURLPath != null)
				return false;
		} else if (!configurationURLPath.equals(other.configurationURLPath))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (projectURLPath == null) {
			if (other.projectURLPath != null)
				return false;
		} else if (!projectURLPath.equals(other.projectURLPath))
			return false;
		if (runURLPath == null) {
			if (other.runURLPath != null)
				return false;
		} else if (!runURLPath.equals(other.runURLPath))
			return false;
		if (serviceURLPath == null) {
			if (other.serviceURLPath != null)
				return false;
		} else if (!serviceURLPath.equals(other.serviceURLPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " (" + id + ")";
	}
}
