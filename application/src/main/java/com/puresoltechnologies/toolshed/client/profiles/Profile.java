package com.puresoltechnologies.toolshed.client.profiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.toolshed.client.profiles.graph.ClassVertex;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraph;
import com.puresoltechnologies.toolshed.client.profiles.graph.MethodVertex;

public class Profile {

    private final List<ProfileEntry> profileEntries = new ArrayList<>();
    private final CodeGraph codeGraph = new CodeGraph();
    private final File rawProfileFile;

    public Profile(File rawProfileFile) {
	super();
	this.rawProfileFile = rawProfileFile;
    }

    public void read() throws IOException {
	try (RawProfileReader rawProfileReader = new RawProfileReader(rawProfileFile)) {
	    rawProfileReader.iterable().forEach(profileEntries::add);
	}

	File idsFile = new File(rawProfileFile.getParentFile(), "ids");
	try (IdsReader reader = new IdsReader(idsFile, codeGraph)) {
	    reader.read();
	}
    }

    public List<ProfileEntry> getEntries() {
	return profileEntries;
    }

    public ClassVertex findClass(String className) {
	return codeGraph.findClass(className);
    }

    public MethodVertex findMethod(ProfileEntry profileEntry) {
	return findMethod(profileEntry.getClassName(), profileEntry.getMethodName(), profileEntry.getDescriptor());
    }

    public MethodVertex findMethod(String className, String methodName, String descriptor) {
	return codeGraph.findMethod(className, methodName, descriptor);
    }

    public ProfileEntry findEntry(MethodVertex invokedMethod) {
	String className = invokedMethod.getClassName();
	String methodName = invokedMethod.getMethodName();
	String descriptor = invokedMethod.getDescriptor();
	for (ProfileEntry profileEntry : profileEntries) {
	    if (className.equals(profileEntry.getClassName())) {
		if (methodName.equals(profileEntry.getMethodName())) {
		    if (descriptor.equals(profileEntry.getDescriptor())) {
			return profileEntry;
		    }
		}
	    }
	}
	return null;
    }

}
