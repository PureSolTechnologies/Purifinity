package com.puresol.commons.license.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a semantic version as specified at http://semver.org.
 * This class is implemented to check the constraints as specified in the
 * specification as well as the presidence behavior.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Version implements Comparable<Version> {

	private static final Pattern stringConstraints = Pattern
			.compile("[0-9a-zA-Z.]+");

	private final int major;
	private final int minor;
	private final int patch;
	private final String preReleaseIdentifier;
	private final String metaData;
	private final String stringRepresentation;

	/**
	 * This is the minimum information constructor for this class. At least
	 * these information need to be provided.
	 * 
	 * @param major
	 *            is the major version number as specified in the specification.
	 * @param minor
	 *            is the minor version number as specified in the specification.
	 * @param patch
	 *            is the patch number as specified in the specification.
	 */
	public Version(int major, int minor, int patch) {
		this(major, minor, patch, null, null);
	}

	/**
	 * This constructor takes the minimum required version numbers and an
	 * additional pre-release identifier.
	 * 
	 * @param major
	 *            is the major version number as specified in the specification.
	 * @param minor
	 *            is the minor version number as specified in the specification.
	 * @param patch
	 *            is the patch number as specified in the specification.
	 * @param preReleaseIdentifier
	 *            is the pre-release identifier as specified in the
	 *            specification. This release identifier may be null.
	 */
	public Version(int major, int minor, int patch, String preReleaseIdentifier) {
		this(major, minor, patch, preReleaseIdentifier, null);
	}

	/**
	 * 
	 * @param major
	 *            is the major version number as specified in the specification.
	 * @param minor
	 *            is the minor version number as specified in the specification.
	 * @param patch
	 *            is the patch number as specified in the specification.
	 * @param preReleaseIdentifier
	 *            is the pre-release identifier as specified in the
	 *            specification. This release identifier may be null.
	 * @param metaData
	 *            is an additional meta data part which is used as specified in
	 *            the specification.
	 */
	public Version(int major, int minor, int patch,
			String preReleaseIdentifier, String metaData) {
		if (major < 0) {
			throw new IllegalArgumentException(
					"The major number must not be negative.");
		}
		this.major = major;
		if (minor < 0) {
			throw new IllegalArgumentException(
					"The minor number must not be negative.");
		}
		this.minor = minor;
		if (patch < 0) {
			throw new IllegalArgumentException(
					"The patch number must not be negative.");
		}
		this.patch = patch;
		if ((preReleaseIdentifier != null)
				&& (!stringConstraints.matcher(preReleaseIdentifier).matches())) {
			throw new IllegalArgumentException(
					"The pre-release identifier must only contain contain alpha numeric values and dots.");
		}
		this.preReleaseIdentifier = preReleaseIdentifier;
		if ((metaData != null)
				&& (!stringConstraints.matcher(metaData).matches())) {
			throw new IllegalArgumentException(
					"The meta data must only contain contain alpha numeric values and dots.");
		}
		this.metaData = metaData;
		String string = buildStringRepresentation();
		stringRepresentation = string;
	}

	private String buildStringRepresentation() {
		StringBuilder builder = new StringBuilder();
		builder.append(major);
		builder.append(".");
		builder.append(minor);
		builder.append(".");
		builder.append(patch);
		if (preReleaseIdentifier != null) {
			builder.append("-");
			builder.append(preReleaseIdentifier);
		}
		if (metaData != null) {
			builder.append("+");
			builder.append(metaData);
		}
		String string = builder.toString();
		return string;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	public String getPreReleaseIdentifier() {
		return preReleaseIdentifier;
	}

	public String getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return stringRepresentation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((stringRepresentation == null) ? 0 : stringRepresentation
						.hashCode());
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
		Version other = (Version) obj;
		if (stringRepresentation == null) {
			if (other.stringRepresentation != null)
				return false;
		} else if (!stringRepresentation.equals(other.stringRepresentation))
			return false;
		return true;
	}

	@Override
	public int compareTo(Version other) {
		if (this.major != other.major) {
			return Integer.valueOf(this.major).compareTo(other.major);
		}
		if (this.minor != other.minor) {
			return Integer.valueOf(this.minor).compareTo(other.minor);
		}
		if (this.patch != other.patch) {
			return Integer.valueOf(this.patch).compareTo(other.patch);
		}
		int preReleaseCompare = comparePreReleaseInformation(
				this.preReleaseIdentifier, other.preReleaseIdentifier);
		if (preReleaseCompare != 0) {
			return preReleaseCompare;
		}
		// Build metadata does not figure into precedence!
		return 0;
	}

	private int comparePreReleaseInformation(String myPreRelease,
			String otherPreRelease) {
		if ((myPreRelease == null) && (otherPreRelease == null)) {
			return 0;
		} else if ((myPreRelease == null) && (otherPreRelease != null)) {
			/*
			 * we do not have pre-release information, so we are an official
			 * release
			 */
			return 1;
		} else if ((myPreRelease != null) && (otherPreRelease == null)) {
			/*
			 * the other does not have pre-release information, so the other is
			 * an official release
			 */
			return -1;
		}
		String[] mySplits = myPreRelease.split("\\.");
		String[] otherSplits = otherPreRelease.split("\\.");
		for (int i = 0; i < Math.min(mySplits.length, otherSplits.length); i++) {
			String mySplit = mySplits[i];
			String otherSplit = otherSplits[i];
			int compareResult = comparePreReleaseParts(mySplit, otherSplit);
			if (compareResult != 0) {
				return compareResult;
			}
		}
		if (mySplits.length != otherSplits.length) {
			if (mySplits.length > otherSplits.length) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}

	private int comparePreReleaseParts(String myPart, String otherPart) {
		Integer my = null;
		try {
			my = Integer.valueOf(myPart);
		} catch (NumberFormatException e) {
		}
		Integer other = null;
		try {
			other = Integer.valueOf(otherPart);
		} catch (NumberFormatException e) {
		}
		if ((my == null) && (other == null)) {
			return myPart.compareTo(otherPart);
		} else if ((my != null) && (other == null)) {
			return 1;
		} else if ((my == null) && (other != null)) {
			return -1;
		}
		return my.compareTo(other);
	}

	public static Version fromString(String versionString) {
		Pattern pattern = Pattern
				.compile("^(\\d+)\\.(\\d+)\\.(\\d+)(-([0-9a-zA-Z.]+))?(\\+([0-9a-zA-Z.]+))?$");
		Matcher matcher = pattern.matcher(versionString);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("The given version string '"
					+ versionString + "' is not valid.");
		}
		int major = Integer.valueOf(matcher.group(1));
		int minor = Integer.valueOf(matcher.group(2));
		int patch = Integer.valueOf(matcher.group(3));
		String preRelease = matcher.group(5);
		String metaData = matcher.group(7);
		return new Version(major, minor, patch, preRelease, metaData);
	}
}
