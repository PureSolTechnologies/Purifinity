package com.puresol.utils.math;

public abstract class AbstractParameter implements Parameter {

	private static final long serialVersionUID = 7556745066178507744L;

	private final String name;
	private final LevelOfMeasurement levelOfMeasurement;
	private final String description;

	public AbstractParameter(String name,
			LevelOfMeasurement levelOfMeasurement, String description) {
		super();
		this.name = name;
		this.levelOfMeasurement = levelOfMeasurement;
		this.description = description;
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

}
