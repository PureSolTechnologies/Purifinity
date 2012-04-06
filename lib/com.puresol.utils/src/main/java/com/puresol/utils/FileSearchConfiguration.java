package com.puresol.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the file search configuration for the file search
 * facilities in FileSearch class. Several settings specify the behavior which
 * are handles here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileSearchConfiguration implements Serializable {

    private static final long serialVersionUID = 4724648711481875290L;

    /**
     * Contains FS patterns which specify directory names which are to be
     * included no matter what is specified in directoryExcludes.
     */
    private final List<String> dirIncludes = new ArrayList<String>();
    /**
     * These list contains FS pattern for directories which are to be ignored.
     * An exception is the definition within directoryIncludes which overrides
     * the excludes list.
     */
    private final List<String> dirExcludes = new ArrayList<String>();
    /**
     * Contains FS patterns which specify file names which are to be included no
     * matter what is specified in fileExcludes.
     */
    private final List<String> fileIncludes = new ArrayList<String>();
    /**
     * These list contains FS pattern for files which are to be ignored. An
     * exception is the definition within fileIncludes which overrides the
     * excludes list.
     */
    private final List<String> fileExcludes = new ArrayList<String>();
    /**
     * This flag specifies whether hidden file should be ignored by defaults.
     * This flag is used when neither in includeXXX nor in excludeXXX a matching
     * FS pattern is defined.
     */
    private boolean ignoreHidden = true;

    /**
     * This is the default constructor. No includes and excludes are defined and
     * ignoreHidden is set to true.
     */
    public FileSearchConfiguration() {
	super();
    }

    /**
     * This constructor sets the initial value for this class.
     * 
     * @param dirIncludes
     * @param dirExcludes
     * @param fileIncludes
     * @param fileExcludes
     * @param ignoreHidden
     */
    public FileSearchConfiguration(List<String> dirIncludes,
	    List<String> dirExcludes, List<String> fileIncludes,
	    List<String> fileExcludes, boolean ignoreHidden) {
	super();
	setDirectoryIncludes(dirIncludes);
	setDirectoryExcludes(dirExcludes);
	setFileIncludes(fileIncludes);
	setFileExcludes(fileExcludes);
	setIgnoreHidden(ignoreHidden);
    }

    public final void setDirectoryIncludes(List<String> dirIncludes) {
	this.dirIncludes.clear();
	this.dirIncludes.addAll(dirIncludes);
    }

    public final List<String> getDirectoryIncludes() {
	return dirIncludes;
    }

    public final void setDirectoryExcludes(List<String> dirExcludes) {
	this.dirExcludes.clear();
	this.dirExcludes.addAll(dirExcludes);
    }

    public final List<String> getDirectoryExcludes() {
	return dirExcludes;
    }

    public final void setFileIncludes(List<String> fileIncludes) {
	this.fileIncludes.clear();
	this.fileIncludes.addAll(fileIncludes);
    }

    public final List<String> getFileIncludes() {
	return fileIncludes;
    }

    public final void setFileExcludes(List<String> fileExcludes) {
	this.fileExcludes.clear();
	this.fileExcludes.addAll(fileExcludes);
    }

    public final List<String> getFileExcludes() {
	return fileExcludes;
    }

    public final void setIgnoreHidden(boolean ignoreHidden) {
	this.ignoreHidden = ignoreHidden;
    }

    public final boolean isIgnoreHidden() {
	return ignoreHidden;
    }

}
