package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class FileSearchConfiguration {

	/**
	 * Contains FS patterns which specify directory names which are to be
	 * included no matter what is specified in directoryExcludes.
	 */
	private final List<String> locationIncludes = new ArrayList<>();
	/**
	 * These list contains FS pattern for directories which are to be ignored.
	 * An exception is the definition within directoryIncludes which overrides
	 * the excludes list.
	 */
	private final List<String> locationExcludes = new ArrayList<>();
	/**
	 * Contains FS patterns which specify file names which are to be included no
	 * matter what is specified in fileExcludes.
	 */
	private final List<String> fileIncludes = new ArrayList<>();
	/**
	 * These list contains FS pattern for files which are to be ignored. An
	 * exception is the definition within fileIncludes which overrides the
	 * excludes list.
	 */
	private final List<String> fileExcludes = new ArrayList<>();
	/**
	 * This flag specifies whether hidden file should be ignored by defaults.
	 * This flag is used when neither in includeXXX nor in excludeXXX a matching
	 * FS pattern is defined.
	 */
	private final boolean ignoreHidden;

	/**
	 * This constructor sets the initial value for this class.
	 * 
	 * @param locationIncludes
	 * @param locationExcludes
	 * @param fileIncludes
	 * @param fileExcludes
	 * @param ignoreHidden
	 */
	public FileSearchConfiguration(
			@JsonProperty("locationIncludes") List<String> locationIncludes,
			@JsonProperty("locationExcludes") List<String> locationExcludes,
			@JsonProperty("fileIncludes") List<String> fileIncludes,
			@JsonProperty("fileExcludes") List<String> fileExcludes,
			@JsonProperty("ignoreHidden") boolean ignoreHidden) {
		super();
		if (fileIncludes != null) {
			this.fileIncludes.addAll(fileIncludes);
		}
		if (fileExcludes != null) {
			this.fileExcludes.addAll(fileExcludes);
		}
		if (locationIncludes != null) {
			this.locationIncludes.addAll(locationIncludes);
		}
		if (locationExcludes != null) {
			this.locationExcludes.addAll(locationExcludes);
		}
		this.ignoreHidden = ignoreHidden;
	}

	public final List<String> getLocationIncludes() {
		return locationIncludes;
	}

	public final List<String> getLocationExcludes() {
		return locationExcludes;
	}

	public final List<String> getFileIncludes() {
		return fileIncludes;
	}

	public final List<String> getFileExcludes() {
		return fileExcludes;
	}

	public final boolean isIgnoreHidden() {
		return ignoreHidden;
	}

}
