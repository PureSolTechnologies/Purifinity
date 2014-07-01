package com.puresoltechnologies.purifinity.server.domain.evaluation;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

public class EvaluatorPluginInformation implements Serializable {

    private static final long serialVersionUID = -1867488685418681896L;

    private final String id;
    private final String name;
    private final Version pluginVersion;
    private final String jndiName;
    private final String description;
    private final Set<QualityCharacteristic> qualityCharacteristics = new HashSet<>();
    private final Set<Parameter<?>> parameters = new HashSet<>();
    private final Set<String> dependencies = new HashSet<>();

    public EvaluatorPluginInformation(
	    @JsonProperty("id") String id,
	    @JsonProperty("name") String name,
	    @JsonProperty("pluginVersion") Version pluginVersion,
	    @JsonProperty("jndiName") String jndiName,
	    @JsonProperty("description") String description,
	    @JsonProperty("qualityCharacteristics") Set<QualityCharacteristic> qualityCharacteristics,
	    @JsonProperty("parameters") Set<Parameter<?>> parameters,
	    @JsonProperty("dependencies") Set<String> dependencies) {
	super();
	this.id = id;
	this.name = name;
	this.pluginVersion = pluginVersion;
	this.jndiName = jndiName;
	this.description = description;
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

    public Version getPluginVersion() {
	return pluginVersion;
    }

    public String getJndiName() {
	return jndiName;
    }

    public String getDescription() {
	return description;
    }

    /**
     * This method returns a list with all quality characteristics which are
     * evaluated with the evaluator created with this factory.
     * 
     * @return
     */
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
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
