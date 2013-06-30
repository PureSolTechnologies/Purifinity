package com.puresol.purifinity.utils.math;

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
		AbstractParameter<?> other = (AbstractParameter<?>) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
