package com.puresol.coding.java.antlr;

public class JavaMethod {

	private String modifiers;
	private String name;

	public JavaMethod(String name, String modifiers) {
		this.modifiers = modifiers;
		this.name = name;
	}

	public String getModifiers() {
		return modifiers;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return modifiers + " " + name;
	}
}
