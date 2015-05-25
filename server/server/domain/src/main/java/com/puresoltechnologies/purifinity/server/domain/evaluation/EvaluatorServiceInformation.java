package com.puresoltechnologies.purifinity.server.domain.evaluation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceInformation;
import com.puresoltechnologies.versioning.Version;

public class EvaluatorServiceInformation implements ServiceInformation {

	private static final long serialVersionUID = 8322672649687092409L;

	private final String id;
	private final String name;
	private final EvaluatorType type;
	private final Version pluginVersion;
	private final String jndiName;
	private final String description;
	private final List<ConfigurationParameter<?>> configurationParameters = new ArrayList<>();
	private final String serviceURLPath;
	private final String configurationURLPath;
	private final String projectURLPath;
	private final String runURLPath;
	private final Set<QualityCharacteristic> qualityCharacteristics = new HashSet<>();
	private final Set<MetricParameter<?>> parameters = new HashSet<>();
	private final Set<String> dependencies = new HashSet<>();

	public EvaluatorServiceInformation(
			@JsonProperty("id") String id,
			@JsonProperty("name") String name,
			@JsonProperty("type") EvaluatorType type,
			@JsonProperty("pluginVersion") Version pluginVersion,
			@JsonProperty("jndiName") String jndiName,
			@JsonProperty("description") String description,
			@JsonProperty("configurationParameters") List<ConfigurationParameter<?>> configurationParameters,
			@JsonProperty("serviceURLPath") String serviceURLPath,
			@JsonProperty("configurationURLPath") String configurationURLPath,
			@JsonProperty("projectURLPath") String projectURLPath,
			@JsonProperty("runURLPath") String runURLPath,
			@JsonProperty("qualityCharacteristics") Set<QualityCharacteristic> qualityCharacteristics,
			@JsonProperty("parameters") Set<MetricParameter<?>> parameters,
			@JsonProperty("dependencies") Set<String> dependencies) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.pluginVersion = pluginVersion;
		this.jndiName = jndiName;
		this.description = description;
		this.configurationParameters.addAll(configurationParameters);
		this.serviceURLPath = serviceURLPath;
		this.configurationURLPath = configurationURLPath;
		this.projectURLPath = projectURLPath;
		this.runURLPath = runURLPath;
		this.qualityCharacteristics.addAll(qualityCharacteristics);
		this.parameters.addAll(parameters);
		this.dependencies.addAll(dependencies);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public EvaluatorType getType() {
		return type;
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

	/**
	 * This method returns a list with all quality characteristics which are
	 * evaluated with the evaluator created with this factory.
	 * 
	 * @return
	 */
	public Set<QualityCharacteristic> getQualityCharacteristics() {
		return qualityCharacteristics;
	}

	public Set<MetricParameter<?>> getParameters() {
		return parameters;
	}

	/**
	 * This method returns a list of Evaluator classes which are needed to be
	 * evaluated before the evaluator created by this factory can be run.
	 * 
	 * @return
	 */
	public Set<String> getDependencies() {
		return dependencies;
	}

	@Override
	public String toString() {
		return getName() + " " + "(" + getJndiName() + ")";
	}
}
