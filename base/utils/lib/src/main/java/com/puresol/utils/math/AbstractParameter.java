package com.puresol.utils.math;

public abstract class AbstractParameter<T> implements Parameter<T> {

    private static final long serialVersionUID = 7556745066178507744L;

    private final String name;
    private final LevelOfMeasurement levelOfMeasurement;
    private final String description;
    private final Class<T> type;

    public AbstractParameter(String name,
	    LevelOfMeasurement levelOfMeasurement, String description,
	    Class<T> type) {
	super();
	this.name = name;
	this.levelOfMeasurement = levelOfMeasurement;
	this.description = description;
	this.type = type;
    }

    @Override
    public final String getName() {
	return name;
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

}
