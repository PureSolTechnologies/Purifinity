package com.puresol.coding.lang.cpp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles and contains all directories which are used for include
 * macros.
 * 
 * @author Rick-Rainer Ludwig
 */
public class IncludeDirectories {

    private static final Object INSTANCE_LOCK = new Object();
    private static IncludeDirectories instance = null;

    public static IncludeDirectories getInstance() {
	if (instance == null) {
	    synchronized (INSTANCE_LOCK) {
		if (instance == null) {
		    instance = new IncludeDirectories();
		}
	    }
	}
	return instance;
    }

    private final List<File> directories = new ArrayList<File>();

    public List<File> getDirectories() {
	return directories;
    }

    public void addDirectory(File directory) {
	directories.add(directory);
    }

    public void removeDirectory(File directory) {
	directories.remove(directory);
    }
}
