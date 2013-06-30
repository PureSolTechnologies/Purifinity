package com.puresol.purifinity.coding.lang.c11.preprocessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles and contains all directories which are used for include
 * macros.
 * 
 * The directories in here are defined as {@link File} objects due to eh normal
 * behavior to include only files from the current source repository or from the
 * current operating system.
 * 
 * @author Rick-Rainer Ludwig
 */
public class IncludeDirectories {

    /**
     * This field contains all directories added to this object.
     */
    private final List<File> directories = new ArrayList<File>();

    /**
     * This method returns all defined directories.
     * 
     * @return A {@link List} of {@link File} is returned containing all defined
     *         directories.
     */
    public List<File> getDirectories() {
	return directories;
    }

    /**
     * This method adds a new directory to this object. For stability, all
     * directories put in here should be absolute paths. Relative paths should
     * be transformed before into absolute paths. The implementation might not
     * conserve the current path.
     * 
     * @param directory
     *            is the directory to be added.
     */
    public void addDirectory(File directory) {
	directories.add(directory);
    }

    /**
     * Removes a formerly defined directory.
     * 
     * @param directory
     *            is the directory to be removed.
     */
    public void removeDirectory(File directory) {
	directories.remove(directory);
    }
}
