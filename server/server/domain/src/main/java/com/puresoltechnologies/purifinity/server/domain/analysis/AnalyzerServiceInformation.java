package com.puresoltechnologies.purifinity.server.domain.analysis;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.versioning.Version;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceInformation;

public class AnalyzerServiceInformation implements ServiceInformation {

	private static final long serialVersionUID = 8381755953924455072L;

	private final String id;
	private final String name;
	private final String version;
	private final Version pluginVersion;
	private final String jndiName;
	private final String description;
	private final String serviceURLPath;
	private final String configurationURLPath;
	private final String projectURLPath;
	private final String runURLPath;

	public AnalyzerServiceInformation(@JsonProperty("id") String id,
			@JsonProperty("name") String name,
			@JsonProperty("version") String version,
			@JsonProperty("pluginVersion") Version pluginVersion,
			@JsonProperty("jndiName") String jndiName,
			@JsonProperty("description") String description,
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

	@Override
	public String toString() {
		return getName() + " " + getVersion() + "(" + getJndiName() + ")";
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
