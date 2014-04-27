package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Properties;

import org.codehaus.jackson.annotate.JsonProperty;

public class AnalysisProjectSettings {

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
	private final Properties repositoryLocation;

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
	 * @param repositoryLocation
	 *            is the source directory to be search for analysis.
	 */
	public AnalysisProjectSettings(
			@JsonProperty("name") String name,
			@JsonProperty("description") String description,
			@JsonProperty("fileSearchConfiguration") FileSearchConfiguration fileSearchConfiguration,
			@JsonProperty("respositoryLocation") Properties repositoryLocation) {
		super();
		this.name = name;
		this.description = description;
		this.fileSearchConfiguration = fileSearchConfiguration;
		this.repositoryLocation = repositoryLocation;
	}

	/**
	 * Returns the analysis name.
	 * 
	 * @return A String is returned.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Returns the description of the analysis.
	 * 
	 * @return A String is returned.
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * This method returns the file search configuration.
	 * 
	 * @return A {@link FileSearchConfiguration} is returned.
	 */
	public final FileSearchConfiguration getFileSearchConfiguration() {
		return fileSearchConfiguration;
	}

	public final Properties getRepositoryLocation() {
		return repositoryLocation;
	}
}
