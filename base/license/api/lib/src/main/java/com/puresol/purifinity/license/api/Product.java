package com.puresol.purifinity.license.api;

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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((version == null) ? 0 : version.hashCode());
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
	Product other = (Product) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (version == null) {
	    if (other.version != null)
		return false;
	} else if (!version.equals(other.version))
	    return false;
	return true;
    }

}
