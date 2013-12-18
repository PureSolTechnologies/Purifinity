package com.puresoltechnologies.purifinity.jboss.test.arquillian;

import java.io.File;

import org.jboss.shrinkwrap.api.Filter;

/**
 * This filter only exclued hidden files with a dot in front '.*' and class
 * filed '*.class'.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DefaultResourceFilter implements Filter<File> {

	/**
	 * Overrides {@link Filter#include(Object)} to check for resource files. The
	 * excludes are sorted for speed up.
	 */
	@Override
	public boolean include(File file) {
		if (file.getName().endsWith(".class")) {
			// this is most likely
			return false;
		}
		if (file.getName().startsWith(".")) {
			// this is less likely
			return false;
		}
		return true;
	}

}
