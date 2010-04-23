package com.puresol.utils.di;

/**
 * This class represents a single injection for dependency injection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Injection {

    public static Injection named(String name, Object object) {
	return new Injection(name, object);
    }

    public static Injection unnamed(Object object) {
	return new Injection(object);
    }

    private final String name;
    private final Object object;

    private Injection(String name, Object object) {
	this.name = name;
	this.object = object;
    }

    private Injection(Object object) {
	this.name = "";
	this.object = object;
    }

    public String getName() {
	return name;
    }

    public Object getObject() {
	return object;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((object == null) ? 0 : object.hashCode());
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
	Injection other = (Injection) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (object == null) {
	    if (other.object != null)
		return false;
	} else if (!object.equals(other.object))
	    return false;
	return true;
    }

}
