package com.puresol.coding.java.antlr;

public class JavaClass {

	private String modifiers;
	private String name;
	private String extended;
	private String implemented;

	public JavaClass(String name, String modifiers, String extended,
			String implemented) {
		this.modifiers = modifiers;
		this.name = name;
		this.extended = extended;
		this.implemented = implemented;
	}

	public String getModifiers() {
		return modifiers;
	}

	public String getName() {
		return name;
	}

	public String getExtended() {
		return extended;
	}

	public String getImplemented() {
		return implemented;
	}

	public String toString() {
		return modifiers + " " + name + " " + extended + " " + implemented;
	}
}
