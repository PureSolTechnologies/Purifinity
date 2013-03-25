package com.puresol.coding.evaluation.impl;

import java.io.Serializable;

/**
 * This class contains a single result from an evaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Result implements Serializable, Comparable<Result> {

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

	@Override
	public String toString() {
		String result = name + " - " + description;
		if ((unit != null) && (!unit.isEmpty())) {
			result += " [" + unit + "]";
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Result other = (Result) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public int compareTo(Result other) {
		if (this.value < other.value)
			return -1;
		if (this.value > other.value)
			return 1;
		return 0;
	}

}
