package com.puresoltechnologies.purifinity.server.domain.repositories;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.math.Parameter;

/**
 * This class contains all information about an available repository type.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RepositoryType implements Serializable {

	private static final long serialVersionUID = 1607123633941143239L;

	public final String className;
	public final String name;
	public final String description;
	public final Map<String, Parameter<?>> parameters = new LinkedHashMap<>();

	public RepositoryType(@JsonProperty("className") String className,
			@JsonProperty("name") String name,
			@JsonProperty("description") String description,
			@JsonProperty("parameters") Map<String, Parameter<?>> parameters) {
		super();
		this.className = className;
		this.name = name;
		this.description = description;
		this.parameters.putAll(parameters);
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

}
