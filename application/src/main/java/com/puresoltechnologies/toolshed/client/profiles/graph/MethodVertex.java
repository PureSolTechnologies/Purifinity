package com.puresoltechnologies.toolshed.client.profiles.graph;

import java.util.Objects;

public class MethodVertex extends CodeGraphVertex {

    private final String className;
    private final String methodName;
    private final int hash;

    public MethodVertex(String className, String methodName) {
	this.className = className;
	this.methodName = methodName;
	this.hash = Objects.hash(className, methodName);
    }

    public String getClassName() {
	return className;
    }

    public String getMethodName() {
	return methodName;
    }

    public void addInvokation(InvokesEdge invokesEdge) {
	invokesEdge.addStartVertex(this);
	addEdge(invokesEdge);
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
	MethodVertex other = (MethodVertex) obj;
	if (hash != other.hash)
	    return false;
	if (className == null) {
	    if (other.className != null)
		return false;
	} else if (!className.equals(other.className))
	    return false;
	if (methodName == null) {
	    if (other.methodName != null)
		return false;
	} else if (!methodName.equals(other.methodName))
	    return false;
	return true;
    }

}
