package com.puresol.license.api;

public class Product {

	private final String name;
	private final String version;

	public Product(String name, String version) {
		super();
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
