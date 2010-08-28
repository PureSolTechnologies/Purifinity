package com.puresol.uhura.grammar.production;

import java.util.ArrayList;
import java.util.List;

public class Production {

	private final String name;
	private final String alternativeName;
	private final List<Construction> constructions = new ArrayList<Construction>();
	private boolean node = true;

	public Production(String name) {
		super();
		this.name = name;
		this.alternativeName = name;
	}

	public Production(String name, String alternativeName) {
		super();
		this.name = name;
		this.alternativeName = alternativeName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the alternativeName
	 */
	public String getAlternativeName() {
		return alternativeName;
	}

	public void addConstruction(Construction construction) {
		constructions.add(construction);
	}

	public void addAllConstructions(List<Construction> constructions) {
		this.constructions.addAll(constructions);
	}

	/**
	 * @return the elements
	 */
	public List<Construction> getConstructions() {
		return constructions;
	}

	public boolean isEmpty() {
		return (constructions.size() == 0);
	}

	/**
	 * @return the node
	 */
	public boolean isNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(boolean node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return toString(-1);
	}

	public String toString(int itemPosition) {
		StringBuffer result = new StringBuffer(name);
		if (alternativeName.equals(name)) {
			result.append(" {");
			result.append(alternativeName);
			result.append("}");
		}
		result.append(" : ");
		int position = 0;
		for (Construction element : constructions) {
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
		if (!alternativeName.equals(name)) {
			result.append(" {");
			result.append(alternativeName);
			result.append("}");
		}
		result.append(" : ");
		int position = 0;
		for (Construction element : constructions) {
			if (position == itemPosition) {
				result.append(" . ");
			}
			position++;
			result.append(" ");
			result.append(element.toShortString());
		}
		if (itemPosition == constructions.size()) {
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
				+ ((alternativeName == null) ? 0 : alternativeName.hashCode());
		result = prime * result
				+ ((constructions == null) ? 0 : constructions.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (node ? 1231 : 1237);
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
		if (alternativeName == null) {
			if (other.alternativeName != null)
				return false;
		} else if (!alternativeName.equals(other.alternativeName))
			return false;
		if (constructions == null) {
			if (other.constructions != null)
				return false;
		} else if (!constructions.equals(other.constructions))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (node != other.node)
			return false;
		return true;
	}

}
