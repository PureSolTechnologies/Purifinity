package com.puresoltechnologies.commons.math;

public class ParameterWithArbitraryUnit<T> implements Parameter<T> {

	private static final long serialVersionUID = 7556745066178507744L;

	private final String name;
	private final String unit;
	private final LevelOfMeasurement levelOfMeasurement;
	private final String description;
	private final Class<T> type;

	public ParameterWithArbitraryUnit(String name, String unit,
			LevelOfMeasurement levelOfMeasurement, String description,
			Class<T> type) {
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
