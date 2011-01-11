package com.puresol.coding.evaluator;

import java.io.Serializable;

/**
 * This class contains a single result from an evaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -196045641252198642L;

	private final String name;
	private final String description;
	private final double value;
	private final String unit;

	public Result(String name, String description, double value, String unit) {
		super();
		this.name = name;
		this.description = description;
		this.value = value;
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getValue() {
		return value;
	}

	public String getUnit() {
		return unit;
	}

}
