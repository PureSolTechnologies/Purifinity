package com.puresoltechnologies.purifinity.jboss.test.arquillian;

import java.io.File;

/**
 * This class provides resource filtering with additional suffix exclusions.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ResourceFilterForSuffixExclusion extends DefaultResourceFilter {

	/**
	 * This is the list with the excluded suffixes.
	 */
	private final String[] exclusions;

	/**
	 * Creates a new instance of {@code ExcludeEndsWithResourceFilter}.
	 * 
	 * @param excludes
	 *            the defined ends-with names, excluded by this filter
	 */
	public ResourceFilterForSuffixExclusion(String... excludes) {
		if (excludes == null) {
			throw new IllegalArgumentException(
					"The array of excludes must not be null! A zero length array is allowed.");
		}
		this.exclusions = new String[excludes.length];
		for (int i = 0; i < this.exclusions.length; i++) {
			this.exclusions[i] = excludes[i].replace('/', File.separatorChar);
		}
	}

	@Override
	public boolean include(File file) {
		if (!super.include(file)) {
			return false;
		}
		for (String exclusion : exclusions) {
			if (file.getAbsolutePath().endsWith(exclusion)) {
				return false;
			}
		}
		return true;
	}
}
