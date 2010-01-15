package com.puresol.html.css;

import java.util.ArrayList;

public class Style {

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
}
