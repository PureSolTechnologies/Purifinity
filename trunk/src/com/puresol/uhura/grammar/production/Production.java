package com.puresol.uhura.grammar.production;

import java.util.ArrayList;
import java.util.List;

public class Production {

	private final String name;
	private final List<Construction> elements = new ArrayList<Construction>();

	public Production(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void addElement(Construction element) {
		elements.add(element);
	}

	/**
	 * @return the elements
	 */
	public List<Construction> getConstructions() {
		return elements;
	}

	@Override
	public String toString() {
		return toString(-1);
	}

	public String toString(int itemPosition) {
		StringBuffer result = new StringBuffer(name);
		result.append(" --> ");
		int position = 0;
		for (Construction element : elements) {
			if (position == itemPosition) {
				result.append(" . ");
			}
			position++;
			result.append(" ");
			result.append(element);
		}
		return result.toString();
	}

	public String toShortString(int itemPosition) {
		StringBuffer result = new StringBuffer(name);
		result.append(" --> ");
		int position = 0;
		for (Construction element : elements) {
			if (position == itemPosition) {
				result.append(" . ");
			}
			position++;
			result.append(" ");
			result.append(element.toShortString());
		}
		if (itemPosition == elements.size()) {
			result.append(" . ");
		}
		return result.toString();
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
				+ ((elements == null) ? 0 : elements.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Production other = (Production) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
