package com.puresoltechnologies.toolshed.client.profiles.graph;

import java.util.Objects;

public class ClassVertex extends CodeGraphVertex {

    private final String className;
    private final int hash;

    public ClassVertex(String className) {
	this.className = className;
	this.hash = Objects.hash(className);
    }

    public String getClassName() {
	return className;
    }

    @Override
    public int hashCode() {
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ClassVertex other = (ClassVertex) obj;
	if (hash != other.hash)
	    return false;
	if (className == null) {
	    if (other.className != null)
		return false;
	} else if (!className.equals(other.className))
	    return false;
	return true;
    }

    public void addImplementation(ImplementsEdge implementsEdge) {
	implementsEdge.addStartVertex(this);
	addEdge(implementsEdge);
    }

}
