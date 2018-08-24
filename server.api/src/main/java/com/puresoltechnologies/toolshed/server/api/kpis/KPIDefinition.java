package com.puresoltechnologies.toolshed.server.api.kpis;

import java.util.Objects;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public abstract class KPIDefinition {

    private final String name;
    private final String unit;
    private final String description;
    private final LevelOfMeasurement levelOfMeasurement;
    private final int hashCode;

    public KPIDefinition(String name, String unit, String description, LevelOfMeasurement levelOfMeasurement) {
	super();
	this.name = name;
	this.unit = unit;
	this.description = description;
	this.levelOfMeasurement = levelOfMeasurement;
	this.hashCode = Objects.hash(name, unit, description, levelOfMeasurement);
    }

    public String getName() {
	return name;
    }

    public String getUnit() {
	return unit;
    }

    public String getDescription() {
	return description;
    }

    public LevelOfMeasurement getLevelOfMeasurement() {
	return levelOfMeasurement;
    }

    @Override
    public int hashCode() {
	return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	KPIDefinition other = (KPIDefinition) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (hashCode != other.hashCode)
	    return false;
	if (levelOfMeasurement != other.levelOfMeasurement)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (unit == null) {
	    if (other.unit != null)
		return false;
	} else if (!unit.equals(other.unit))
	    return false;
	return true;
    }

}
