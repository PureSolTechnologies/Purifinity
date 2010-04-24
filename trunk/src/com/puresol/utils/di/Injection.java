package com.puresol.utils.di;

/**
 * This class represents a single injection for dependency injection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Injection {

	public static Injection named(String name, Object object) {
		return new Injection(name, object);
	}

	public static Injection unnamed(Object object) {
		return new Injection(object);
	}

	private final String name;
	private final Object value;

	private Injection(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	private Injection(Object value) {
		this.name = "";
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		String string = "value: " + value;
		if (value != null) {
			string += " (type: " + value.getClass().getName() + ")";
		} else {
			string += " (type: null)";
		}
		if (!name.isEmpty()) {
			string += " for Inject.value=\"" + name + "\"";
		}
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Injection other = (Injection) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
