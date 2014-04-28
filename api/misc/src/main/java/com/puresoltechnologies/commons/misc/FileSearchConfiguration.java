package com.puresoltechnologies.commons.misc;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class contains the file search configuration for the file search
 * facilities in FileSearch class. Several settings specify the behavior which
 * are handles here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileSearchConfiguration implements Serializable, Cloneable {

	private static final long serialVersionUID = 4724648711481875290L;

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
	 * Contains the hashCode.
	 */
	private final int hashCode;

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
		this.fileIncludes.addAll(fileIncludes);
		this.fileExcludes.addAll(fileExcludes);
		this.locationIncludes.addAll(locationIncludes);
		this.locationExcludes.addAll(locationExcludes);
		this.ignoreHidden = ignoreHidden;
		hashCode = ObjectUtilities.calculateConstantHashCode(locationIncludes,
				locationExcludes, fileIncludes, fileExcludes, ignoreHidden);
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

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSearchConfiguration other = (FileSearchConfiguration) obj;
		if (locationExcludes == null) {
			if (other.locationExcludes != null)
				return false;
		} else if (!locationExcludes.equals(other.locationExcludes))
			return false;
		if (locationIncludes == null) {
			if (other.locationIncludes != null)
				return false;
		} else if (!locationIncludes.equals(other.locationIncludes))
			return false;
		if (fileExcludes == null) {
			if (other.fileExcludes != null)
				return false;
		} else if (!fileExcludes.equals(other.fileExcludes))
			return false;
		if (fileIncludes == null) {
			if (other.fileIncludes != null)
				return false;
		} else if (!fileIncludes.equals(other.fileIncludes))
			return false;
		if (ignoreHidden != other.ignoreHidden)
			return false;
		return true;
	}

	@Override
	public FileSearchConfiguration clone() {
		try {
			FileSearchConfiguration cloned = (FileSearchConfiguration) super
					.clone();
			setFinal(cloned, "ignoreHidden", ignoreHidden);
			setFinal(cloned, "locationIncludes", locationIncludes);
			setFinal(cloned, "locationExcludes", locationExcludes);
			setFinal(cloned, "fileIncludes", fileIncludes);
			setFinal(cloned, "fileExcludes", fileExcludes);
			return cloned;
		} catch (CloneNotSupportedException | SecurityException
				| IllegalArgumentException | NoSuchFieldException
				| IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private <T> void setFinal(FileSearchConfiguration cloned, String fieldName,
			T value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<FileSearchConfiguration> clazz = FileSearchConfiguration.class;
		Field field = clazz.getDeclaredField(fieldName);
		System.out.println(field.toString());
		field.setAccessible(true);
		field.set(cloned, value);
		field.setAccessible(false);
	}
}
