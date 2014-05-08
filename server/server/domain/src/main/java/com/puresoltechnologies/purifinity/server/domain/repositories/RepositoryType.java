package com.puresoltechnologies.purifinity.server.domain.repositories;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

/**
 * This class contains all information about an available repository type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RepositoryType implements Serializable {

	private static final long serialVersionUID = 1607123633941143239L;

	public final String name;
	public final String description;
	public final Map<String, ParameterWithArbitraryUnit<?>> parameters = new LinkedHashMap<>();

	public RepositoryType(
			@JsonProperty("name") String name,
			@JsonProperty("description") String description,
			@JsonProperty("parameters") Map<String, ParameterWithArbitraryUnit<?>> parameters) {
		super();
		this.name = name;
		this.description = description;
		this.parameters.putAll(parameters);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, ParameterWithArbitraryUnit<?>> getParameters() {
		return parameters;
	}

}
