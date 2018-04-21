package com.puresoltechnologies.toolshed.client.profiles.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.graphs.graph.Graph;

public class CodeGraph implements Graph<CodeGraphVertex, CodeGraphEdge> {

    private final Set<CodeGraphVertex> vertices = new HashSet<>();
    private final Map<String, ClassVertex> classNameIndex = new HashMap<>();
    private final Map<String, Map<String, MethodVertex>> methodNameIndex = new HashMap<>();

    public ClassVertex findClass(String className) {
	return classNameIndex.get(className);
    }

    public MethodVertex findMethod(String className, String methodName) {
	Map<String, MethodVertex> classes = methodNameIndex.get(className);
	if (classes == null) {
	    return null;
	}
	return classes.get(methodName);
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
	    Map<String, MethodVertex> classes = methodNameIndex.get(methodVertex.getClassName());
	    if (classes == null) {
		classes = new HashMap<>();
		methodNameIndex.put(methodVertex.getClassName(), classes);
	    }
	    classes.put(methodVertex.getMethodName(), methodVertex);
	}
    }

    @Override
    public Set<CodeGraphVertex> getVertices() {
	return java.util.Collections.unmodifiableSet(vertices);
    }

}
