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
	    rawProfileReader.iterable().forEach(this::addEntry);
	}

	File idsFile = new File(rawProfileFile.getParentFile(), "ids");
	try (IdsReader reader = new IdsReader(idsFile, codeGraph)) {
	    reader.read();
	}
    }

    public void addEntry(ProfileEntry profileEntry) {
	ProfileEntry profileEntry2 = new ProfileEntry(profileEntry.getType(),
		profileEntry.getClassName().replaceAll("/", "."), profileEntry.getMethodName(),
		profileEntry.getTotalTime(), profileEntry.getInvocations());
	profileEntries.add(profileEntry2);
    }

    public List<ProfileEntry> getEntries() {
	return profileEntries;
    }

    public ClassVertex findClass(String className) {
	return codeGraph.findClass(className);
    }

    public MethodVertex findMethod(ProfileEntry profileEntry) {
	return findMethod(profileEntry.getClassName(), profileEntry.getMethodName());
    }

    public MethodVertex findMethod(String className, String methodName) {
	return codeGraph.findMethod(className, methodName);
    }

    public ProfileEntry findEntry(MethodVertex invokedMethod) {
	String className = invokedMethod.getClassName();
	String methodName = invokedMethod.getMethodName();
	for (ProfileEntry profileEntry : profileEntries) {
	    if (className.equals(profileEntry.getClassName()) && methodName.equals(profileEntry.getMethodName())) {
		return profileEntry;
	    }
	}
	return null;
    }

}
