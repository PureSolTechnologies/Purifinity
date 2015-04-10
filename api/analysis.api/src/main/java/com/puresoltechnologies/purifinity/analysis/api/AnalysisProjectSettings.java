package com.puresoltechnologies.purifinity.analysis.api;

import java.io.File;
import java.io.Serializable;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;

/**
 * This immutable value object contains the settings of an analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class AnalysisProjectSettings implements Serializable {

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
	private final Properties repository;

	/**
	 * Default constructor for Jackson.
	 */
	public AnalysisProjectSettings() {
		super();
		this.name = null;
		this.description = null;
		this.fileSearchConfiguration = null;
		this.repository = null;

	}

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
	 * @param repository
	 *            is the source directory to be search for analysis.
	 */
	@JsonCreator
	public AnalysisProjectSettings(
			@JsonProperty("name") String name,
			@JsonProperty("description") String description,
			@JsonProperty("fileSearchConfiguration") FileSearchConfiguration fileSearchConfiguration,
			@JsonProperty("repository") Properties repository) {
		super();
		this.name = name;
		this.description = description;
		this.fileSearchConfiguration = fileSearchConfiguration;
		this.repository = repository;
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

	/**
	 * This method returns the source directory of the files to be analyzed.
	 * 
	 * @return A {@link File} is returned.
	 */
	public final Properties getRepository() {
		return repository;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime
				* result
				+ ((fileSearchConfiguration == null) ? 0
						: fileSearchConfiguration.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((repository == null) ? 0 : repository
						.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisProjectSettings other = (AnalysisProjectSettings) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fileSearchConfiguration == null) {
			if (other.fileSearchConfiguration != null)
				return false;
		} else if (!fileSearchConfiguration
				.equals(other.fileSearchConfiguration))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (repository == null) {
			if (other.repository != null)
				return false;
		} else if (!repository.equals(other.repository))
			return false;
		return true;
	}

}
