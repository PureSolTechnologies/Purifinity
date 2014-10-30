package com.puresoltechnologies.commons.misc.types;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class represents a single password. This class basically keeps a simple
 * {@link String}, but this class was introduced to point developers to the
 * fact, that the object handled here, is a password to enforce some care in
 * handling it.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class Password {

    private final String password;

    /**
     * This default constructor is only convenience for JSON serialization.
     */
    public Password() {
	password = null;
    }

    @JsonCreator
    public Password(@JsonProperty("password") String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return password;
    }

    public String getPassword() {
	return password;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
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
	Password other = (Password) obj;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	return true;
    }

}
