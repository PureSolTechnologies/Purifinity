package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.Serializable;

import com.puresol.utils.FileSearchConfiguration;

/**
 * This immutable value object contains the settings of an analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisSettings implements Serializable {

    private static final long serialVersionUID = -1358261012685866713L;

    /**
     * Name is the name of the analysis to be displayed in UIs.
     */
    private final String name;

    /**
     * The description is a simple string which describes briefly what the
     * analysis is about.
     */
    private final String description;

    /**
     * This field contains the settings for the file search for a new analysis
     * run.
     */
    private final FileSearchConfiguration fileSearchConfiguration;

    /**
     * This is the original source directory which is searched for files for
     * analysis.
     */
    private final File sourceDirectory;

    /**
     * This is the initial value constructor for this object to set all
     * immutable values.
     * 
     * @param name
     *            is the analysis name.
     * @param description
     *            is a description to be shown in UI for users.
     * @param fileSearchConfiguration
     *            is the search configuration for file search for analysis.
     * @param sourceDirectory
     *            is the source directory to be search for analysis.
     */
    public AnalysisSettings(String name, String description,
	    FileSearchConfiguration fileSearchConfiguration,
	    File sourceDirectory) {
	super();
	this.name = name;
	this.description = description;
	this.fileSearchConfiguration = fileSearchConfiguration;
	this.sourceDirectory = sourceDirectory;
    }

    /**
     * Returns the analysis name.
     * 
     * @return A String is returned.
     */
    public String getName() {
	return name;
    }

    /**
     * Returns the description of the analysis.
     * 
     * @return A String is returned.
     */
    public String getDescription() {
	return description;
    }

    /**
     * This method returns the file search configuration.
     * 
     * @return A {@link FileSearchConfiguration} is returned.
     */
    public FileSearchConfiguration getFileSearchConfiguration() {
	return fileSearchConfiguration;
    }

    /**
     * This method returns the source directory of the files to be analyzed.
     * 
     * @return A {@link File} is returned.
     */
    public File getSourceDirectory() {
	return sourceDirectory;
    }

}
