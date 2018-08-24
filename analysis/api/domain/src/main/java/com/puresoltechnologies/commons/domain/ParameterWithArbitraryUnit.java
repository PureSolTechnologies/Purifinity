package com.puresoltechnologies.commons.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParameterWithArbitraryUnit<T> implements Parameter<T> {

    private static final long serialVersionUID = 7556745066178507744L;

    private final String name;
    private final String unit;
    private final LevelOfMeasurement levelOfMeasurement;
    private final String description;
    private final Class<T> type;

    public ParameterWithArbitraryUnit(
	    @JsonProperty("name") String name,
	    @JsonProperty("unit") String unit,
	    @JsonProperty("levelOfMeasurement") LevelOfMeasurement levelOfMeasurement,
	    @JsonProperty("description") String description,
	    @JsonProperty("type") Class<T> type) {
	super();
	if ((name == null) || (name.isEmpty())) {
	    throw new IllegalArgumentException(
		    "The name must not be null or empty. No identification is possible otherwise.");
	}
	if (unit == null) {
	    throw new IllegalArgumentException("The unit must not be null.");
	}
	if (levelOfMeasurement == null) {
	    throw new IllegalArgumentException(
		    "The levelOfMeasurement must not be null.");
	}
	if (description == null) {
	    throw new IllegalArgumentException(
		    "The description must not be null.");
	}
	if (type == null) {
	    throw new IllegalArgumentException("The type must not be null.");
	}
	this.name = name;
	this.unit = unit;
	this.levelOfMeasurement = levelOfMeasurement;
	this.description = description;
	this.type = type;
    }

    @Override
    public final String getName() {
	return name;
    }

    @Override
    public final String getUnit() {
	return unit;
    }

    @Override
    public final String getDescription() {
	return description;
    }

    @Override
    public final LevelOfMeasurement getLevelOfMeasurement() {
	return levelOfMeasurement;
    }

    @Override
    public final Class<T> getType() {
	return type;
    }

    @JsonIgnore
    @Override
    public boolean isNumeric() {
	return Number.class.isAssignableFrom(type);
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder(name);
	builder.append(" [").append(type.getSimpleName() + "]");
	return builder.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime
		* result
		+ ((levelOfMeasurement == null) ? 0 : levelOfMeasurement
			.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
	ParameterWithArbitraryUnit<?> other = (ParameterWithArbitraryUnit<?>) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (levelOfMeasurement != other.levelOfMeasurement)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	if (unit == null) {
	    if (other.unit != null)
		return false;
	} else if (!unit.equals(other.unit))
	    return false;
	return true;
    }
}
