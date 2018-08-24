package com.puresoltechnologies.toolshed.client.profiles.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.graphs.graph.Graph;

public class CodeGraph implements Graph<CodeGraphVertex, CodeGraphEdge> {

    private final Set<CodeGraphVertex> vertices = new HashSet<>();
    private final Map<String, ClassVertex> classNameIndex = new HashMap<>();
    private final Map<String, Map<String, Map<String, MethodVertex>>> methodNameIndex = new HashMap<>();

    public ClassVertex findClass(String className) {
	return classNameIndex.get(className);
    }

    public MethodVertex findMethod(String className, String methodName, String descriptor) {
	Map<String, Map<String, MethodVertex>> methodes = methodNameIndex.get(className);
	if (methodes == null) {
	    return null;
	}
	Map<String, MethodVertex> overloadedMethods = methodes.get(methodName);
	if (overloadedMethods == null) {
	    return null;
	}
	return overloadedMethods.get(descriptor);
    }

    public void clear() {
	vertices.clear();
	classNameIndex.clear();
	methodNameIndex.clear();
    }

    public void addVertex(CodeGraphVertex vertex) {
	vertices.add(vertex);
	if (vertex instanceof ClassVertex) {
	    ClassVertex classVertex = (ClassVertex) vertex;
	    classNameIndex.put(classVertex.getClassName(), classVertex);
	}
	if (vertex instanceof MethodVertex) {
	    MethodVertex methodVertex = (MethodVertex) vertex;
	    Map<String, Map<String, MethodVertex>> methods = methodNameIndex.get(methodVertex.getClassName());
	    if (methods == null) {
		methods = new HashMap<>();
		methodNameIndex.put(methodVertex.getClassName(), methods);
	    }
	    Map<String, MethodVertex> overloadedMethods = methods.get(methodVertex.getMethodName());
	    if (overloadedMethods == null) {
		overloadedMethods = new HashMap<>();
		methods.put(methodVertex.getMethodName(), overloadedMethods);
	    }
	    overloadedMethods.put(methodVertex.getDescriptor(), methodVertex);
	}
    }

    @Override
    public Set<CodeGraphVertex> getVertices() {
	return java.util.Collections.unmodifiableSet(vertices);
    }

}
