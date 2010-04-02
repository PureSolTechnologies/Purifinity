package com.puresol.utils;

/**
 * This class represents a description of a single property which might be
 * supported by any class. This description is used for configuration dialogs.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Property implements Comparable<Property> {

	private String name;
	private String description;
	private Class<?> type;
	private String defaultValue;

	public Property(String name, String description, Class<?> type,
			String defaultValue) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.defaultValue = defaultValue;
	}

	public Property(Class<?> clazz, String name, String description,
			Class<?> type, String defaultValue) {
		this.name = clazz.getName() + "." + name;
		this.description = description;
		this.type = type;
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the type
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	@Override
	public String toString() {
		return name + "/" + type.getSimpleName() + "(=" + defaultValue + ") \""
				+ description + "\"";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public int compareTo(Property other) {
		return this.toString().compareTo(other.toString());
	}

}
