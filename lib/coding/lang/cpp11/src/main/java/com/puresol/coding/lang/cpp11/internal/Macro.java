package com.puresol.coding.lang.cpp11.internal;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the information for a single defined preprocessor macro,
 * 
 * @author Rick-Rainer Ludwig
 */
public class Macro {

    private final String name;
    private final String replacement;
    private final List<String> parameters = new ArrayList<String>();
    private final boolean optionalParameters;

    public Macro(String name) {
	this.name = name;
	this.replacement = "1";
	optionalParameters = false;
    }

    public Macro(String name, String replacement) {
	this.name = name;
	this.replacement = replacement;
	optionalParameters = false;
    }

    public Macro(String name, String replacement, List<String> parameters,
	    boolean optionalParameters) {
	this.name = name;
	this.replacement = replacement;
	this.parameters.addAll(parameters);
	this.optionalParameters = optionalParameters;
    }

    public String getName() {
	return name;
    }

    public String getReplacement() {
	return replacement;
    }

    public List<String> getParameters() {
	return parameters;
    }

    public boolean isOptionalParameters() {
	return optionalParameters;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + (optionalParameters ? 1231 : 1237);
	result = prime * result
		+ ((parameters == null) ? 0 : parameters.hashCode());
	result = prime * result
		+ ((replacement == null) ? 0 : replacement.hashCode());
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
	Macro other = (Macro) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (optionalParameters != other.optionalParameters)
	    return false;
	if (parameters == null) {
	    if (other.parameters != null)
		return false;
	} else if (!parameters.equals(other.parameters))
	    return false;
	if (replacement == null) {
	    if (other.replacement != null)
		return false;
	} else if (!replacement.equals(other.replacement))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer(name);
	if ((parameters.size() > 0) || (optionalParameters)) {
	    buffer.append("(");
	    boolean first = true;
	    for (String parameter : parameters) {
		if (first) {
		    first = false;
		} else {
		    buffer.append(",");
		}
		buffer.append(parameter);
	    }
	    if (optionalParameters) {
		if (!first) {
		    buffer.append(",");
		}
		buffer.append("...");
	    }
	    buffer.append(")");
	}
	buffer.append(":\n");
	buffer.append(replacement);
	return buffer.toString();
    }
}
