package com.puresoltechnologies.purifinity.server.domain.evaluation;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
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
    private final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();
    private final String serviceURLPath;
    private final String configurationURLPath;
    private final String projectURLPath;
    private final String runURLPath;
    private final Set<QualityCharacteristic> qualityCharacteristics = new HashSet<>();
    private final Set<Parameter<?>> parameters = new HashSet<>();
    private final Set<String> dependencies = new HashSet<>();

    public EvaluatorServiceInformation(
	    @JsonProperty("id") String id,
	    @JsonProperty("name") String name,
	    @JsonProperty("type") EvaluatorType type,
	    @JsonProperty("pluginVersion") Version pluginVersion,
	    @JsonProperty("jndiName") String jndiName,
	    @JsonProperty("description") String description,
	    @JsonProperty("configurationParameters") Set<ConfigurationParameter<?>> configurationParameters,
	    @JsonProperty("serviceURLPath") String serviceURLPath,
	    @JsonProperty("configurationURLPath") String configurationURLPath,
	    @JsonProperty("projectURLPath") String projectURLPath,
	    @JsonProperty("runURLPath") String runURLPath,
	    @JsonProperty("qualityCharacteristics") Set<QualityCharacteristic> qualityCharacteristics,
	    @JsonProperty("parameters") Set<Parameter<?>> parameters,
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
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
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

    public Set<Parameter<?>> getParameters() {
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
