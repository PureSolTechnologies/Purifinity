package com.puresol.data;


/**
 * This class represents a single part of name like a last name, a middle name
 * or a first name. This parts are used in PersonName to represent an arbitary
 * name, which is used for I18N or L10N.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PersonNamePart implements Cloneable {

    private PersonNamePartType type;
    private String name;

    public PersonNamePart(String name, PersonNamePartType type)
	    throws IllegalNamePartException {
	this.name = name;
	this.type = type;
	check();
    }

    private void check() throws IllegalNamePartException {
	if (name == null) {
	    throw new IllegalNamePartException(this);
	}
	if (name.isEmpty()) {
	    throw new IllegalNamePartException(this);
	}
	if (type == null) {
	    throw new IllegalNamePartException(this);
	}
    }

    @Override
    public String toString() {
	return name;
    }

    public String getName() {
	return name;
    }

    public PersonNamePartType getType() {
	return type;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
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
	PersonNamePart other = (PersonNamePart) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }

    @Override
    public PersonNamePart clone() {
	try {
	    PersonNamePart cloned = (PersonNamePart) super.clone();
	    cloned.name = this.name;
	    cloned.type = this.type;
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new RuntimeException(e);
	}
    }
}
