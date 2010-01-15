package com.puresol.html.css;

public class Attribute {

	private final String attribute;
	private final String value;

	public Attribute(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public String getValue() {
		return value;
	}

	public String toCSSString() {
		return attribute + ":" + value + ";";
	}

}
