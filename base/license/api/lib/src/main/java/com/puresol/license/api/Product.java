package com.puresol.license.api;

public class Product {

	private final String name;
	private final Version version;

	public Product(String name, Version version) {
		super();
		if ((name == null) || (name.isEmpty())) {
			throw new IllegalArgumentException(
					"A product's name must not be null or empty.");
		}
		this.name = name;
		if (version == null) {
			throw new IllegalArgumentException(
					"A product's version must not be null.");
		}
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public Version getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return name + " " + version.toString();
	}

	/**
	 * This method converts a product name (incl. version) into a
	 * {@link Product} object.
	 * 
	 * @param productString
	 * @return
	 */
	public static Product fromString(String productString) {
		String[] splits = productString.split(" ");
		if (splits.length != 2) {
			throw new IllegalArgumentException("Illegal procuct name '"
					+ productString + "' provided.");
		}
		String name = splits[0];
		Version version = Version.fromString(splits[1]);
		return new Product(name, version);
	}
}
