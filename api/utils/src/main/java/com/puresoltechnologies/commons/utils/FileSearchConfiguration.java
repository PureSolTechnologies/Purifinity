package com.puresoltechnologies.commons.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
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
public class FileSearchConfiguration implements Serializable, Cloneable {

	private static final long serialVersionUID = 4724648711481875290L;

	/**
	 * Contains FS patterns which specify directory names which are to be
	 * included no matter what is specified in directoryExcludes.
	 */
	private final List<String> locationIncludes = new ArrayList<String>();
	/**
	 * These list contains FS pattern for directories which are to be ignored.
	 * An exception is the definition within directoryIncludes which overrides
	 * the excludes list.
	 */
	private final List<String> locationExcludes = new ArrayList<String>();
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
		setLocationIncludes(dirIncludes);
		setLocationExcludes(dirExcludes);
		setFileIncludes(fileIncludes);
		setFileExcludes(fileExcludes);
		setIgnoreHidden(ignoreHidden);
	}

	public final void setLocationIncludes(List<String> locationIncludes) {
		this.locationIncludes.clear();
		this.locationIncludes.addAll(locationIncludes);
	}

	public final List<String> getLocationIncludes() {
		return locationIncludes;
	}

	public final void setLocationExcludes(List<String> locationExcludes) {
		this.locationExcludes.clear();
		this.locationExcludes.addAll(locationExcludes);
	}

	public final List<String> getLocationExcludes() {
		return locationExcludes;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((locationExcludes == null) ? 0 : locationExcludes.hashCode());
		result = prime
				* result
				+ ((locationIncludes == null) ? 0 : locationIncludes.hashCode());
		result = prime * result
				+ ((fileExcludes == null) ? 0 : fileExcludes.hashCode());
		result = prime * result
				+ ((fileIncludes == null) ? 0 : fileIncludes.hashCode());
		result = prime * result + (ignoreHidden ? 1231 : 1237);
		return result;
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

			cloned.ignoreHidden = ignoreHidden;
			setFinal(cloned, "locationIncludes", locationIncludes);
			setFinal(cloned, "locationExcludes", locationExcludes);
			setFinal(cloned, "fileIncludes", fileIncludes);
			setFinal(cloned, "fileExcludes", fileExcludes);

			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private <T> void setFinal(FileSearchConfiguration cloned, String fieldName,
			T value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class<FileSearchConfiguration> clazz = FileSearchConfiguration.class;
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println(field.toString());
			if (field.getName().equals(fieldName)) {
				field.setAccessible(true);
				field.set(cloned, value);
				field.setAccessible(false);
			}
		}
	}
}
