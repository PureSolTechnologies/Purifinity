package com.puresoltechnologies.commons.misc;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class contains version information which is to be interpreted as
 * described in Semantic Versioning 2.0.0 (<a
 * href="http://semver.org">semver.org</a>).
 * 
 * @author Rick-Rainer Ludwig
 */
public class Version implements Serializable, Comparable<Version> {

    private static final long serialVersionUID = 4520975035563838544L;

    private static final String identifierRegExp = "[0-9A-Za-z-]+";
    private static final String metadataRegExp = identifierRegExp + "(\\."
	    + identifierRegExp + ")*";
    private static final Pattern identifierPattern = Pattern
	    .compile(metadataRegExp);

    private static final String versionRegExp = "(\\d+)\\.(\\d+)\\.(\\d+)(-("
	    + metadataRegExp + "))?(\\+(" + metadataRegExp + "))?";
    private static final Pattern versionPattern = Pattern
	    .compile(versionRegExp);

    public static Version valueOf(String versionString) {
	Matcher matcher = versionPattern.matcher(versionString);
	if (!matcher.matches()) {
	    throw new IllegalArgumentException(
		    "The version string must match the pattern '"
			    + versionRegExp + "'.");
	}
	int majorVersion = Integer.valueOf(matcher.group(1));
	int minorVersion = Integer.valueOf(matcher.group(2));
	int patchVersion = Integer.valueOf(matcher.group(3));
	String preReleaseInformation = matcher.group(5);
	String buildMetadata = matcher.group(8);
	return new Version(majorVersion, minorVersion, patchVersion,
		preReleaseInformation, buildMetadata);
    }

    private final int major;
    private final int minor;
    private final int patch;
    private final String preReleaseInformation;
    private final String buildMetadata;

    /**
     * This default constructor is only convenience for JSON serialization.
     */
    public Version() {
	major = -1;
	minor = -1;
	patch = -1;
	preReleaseInformation = null;
	buildMetadata = null;
    }

    public Version(int major, int minor, int patch) {
	this(major, minor, patch, null, null);
    }

    public Version(int major, int minor, int patchVersion,
	    String preReleaseInformation) {
	this(major, minor, patchVersion, preReleaseInformation, null);
    }

    @JsonCreator
    public Version(
	    @JsonProperty("major") int major,
	    @JsonProperty("minor") int minor,
	    @JsonProperty("patch") int patch,
	    @JsonProperty("preReleaseInformation") String preReleaseInformation,
	    @JsonProperty("buildMetadata") String buildMetadata) {
	super();
	this.major = major;
	this.minor = minor;
	this.patch = patch;
	if ((preReleaseInformation == null)
		|| (preReleaseInformation.isEmpty())) {
	    this.preReleaseInformation = null;
	} else {
	    this.preReleaseInformation = preReleaseInformation;
	}
	if ((buildMetadata == null) || (buildMetadata.isEmpty())) {
	    this.buildMetadata = null;
	} else {
	    this.buildMetadata = buildMetadata;
	}
	checkContent();
    }

    private final void checkContent() {
	if (major < 0) {
	    throw new IllegalArgumentException(
		    "The major version must not be negative.");
	}
	if (minor < 0) {
	    throw new IllegalArgumentException(
		    "The minor version must not be negative.");
	}
	if (patch < 0) {
	    throw new IllegalArgumentException(
		    "The patch version must not be negative.");
	}
	if (major + minor + patch == 0) {
	    throw new IllegalArgumentException(
		    "The version must contain at least one non zero digit.");
	}
	if (preReleaseInformation != null) {
	    if (!identifierPattern.matcher(preReleaseInformation).matches()) {
		throw new IllegalArgumentException(
			"Pre-release information must match the pattern '"
				+ metadataRegExp + "', but was '"
				+ preReleaseInformation + "'.");
	    }
	}
	if (buildMetadata != null) {
	    if (!identifierPattern.matcher(buildMetadata).matches()) {
		throw new IllegalArgumentException(
			"Build-metadata information must match the pattern '"
				+ metadataRegExp + "', but was '"
				+ buildMetadata + "'.");
	    }
	}
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

    public String getPreReleaseInformation() {
	return preReleaseInformation;
    }

    public String getBuildMetadata() {
	return buildMetadata;
    }

    @Override
    public int compareTo(Version o) {
	if (major != o.major) {
	    return Integer.compare(major, o.major);
	}
	if (minor != o.minor) {
	    return Integer.compare(minor, o.minor);
	}
	if (patch != o.patch) {
	    return Integer.compare(patch, o.patch);
	}
	if (preReleaseInformation == null) {
	    if (o.preReleaseInformation == null) {
		return 0;
	    } else {
		return 1;
	    }
	} else {
	    if (o.preReleaseInformation == null) {
		return -1;
	    }
	    return comparePreReleaseInformation(o);
	}
    }

    private int comparePreReleaseInformation(Version o) {
	// We split the strings into identifiers
	String[] myIdentifiers = preReleaseInformation.split("\\.");
	String[] otherIdentifiers = o.preReleaseInformation.split("\\.");
	// Now we check each identifier position one after another...
	for (int i = 0; i < Math.min(myIdentifiers.length,
		otherIdentifiers.length); i++) {
	    String my = myIdentifiers[i];
	    String other = otherIdentifiers[i];
	    Integer myDigits = convertToDigits(my);
	    Integer otherDigits = convertToDigits(other);
	    if (myDigits == null) {
		if (otherDigits != null) {
		    return 1;
		} else {
		    int result = my.compareTo(other);
		    if (result != 0) {
			return result;
		    }
		}
	    } else {
		if (otherDigits == null) {
		    return -1;
		} else {
		    int result = myDigits.compareTo(otherDigits);
		    if (result != 0) {
			return result;
		    }
		}
	    }
	}
	// Everything is equal, now only the length of the identifiers can
	// decide...
	if (myIdentifiers.length == otherIdentifiers.length) {
	    return 0;
	} else if (myIdentifiers.length > otherIdentifiers.length) {
	    return 1;
	} else {
	    return -1;
	}
    }

    private Integer convertToDigits(String s) {
	try {
	    return Integer.valueOf(s);
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append(major);
	buffer.append('.');
	buffer.append(minor);
	buffer.append('.');
	buffer.append(patch);
	if (preReleaseInformation != null) {
	    buffer.append('-');
	    buffer.append(preReleaseInformation);
	}
	if (buildMetadata != null) {
	    buffer.append('+');
	    buffer.append(buildMetadata);
	}
	return buffer.toString();
    }
}
