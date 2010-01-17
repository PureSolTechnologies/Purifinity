package com.puresol.html.css;

import java.util.ArrayList;
import java.util.Collections;

public class Style implements Comparable<Style> {

	private final ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	private final String element;
	private final String clazz;

	public Style(String element, String clazz) {
		this.element = element;
		this.clazz = clazz;
	}

	public String getElement() {
		return element;
	}

	public String getClazz() {
		return clazz;
	}

	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}

	public String toCSSString() {
		Collections.sort(attributes);
		String css = element;
		if (!clazz.isEmpty()) {
			css += "." + clazz;
		}
		css += " {\n";
		for (Attribute attribute : attributes) {
			css += "\t" + attribute.toCSSString() + "\n";
		}
		css += "}\n";
		return css;
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
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((element == null) ? 0 : element.hashCode());
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
		Style other = (Style) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		return true;
	}

	@Override
	public int compareTo(Style other) {
		String thisString = this.getElement() + "." + this.getClazz();
		String otherString = other.getElement() + "." + other.getClazz();
		return thisString.compareTo(otherString);
	}

}
