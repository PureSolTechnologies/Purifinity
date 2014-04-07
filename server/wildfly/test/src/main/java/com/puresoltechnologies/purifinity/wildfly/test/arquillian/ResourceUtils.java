package com.puresoltechnologies.purifinity.wildfly.test.arquillian;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * This class provides functionality to deal with resources. This class is used
 * in combination with Arquillian tests.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ResourceUtils {

	/**
	 * This method is used to add resource files to a {@link JavaArchive}. The
	 * resource files are searched within a directory provided as parameter.
	 * 
	 * @param javaArchive
	 *            is the {@link JavaArchive} where the found resource files are
	 *            put into.
	 * @param directory
	 *            is the directory to be searched for suitable resource files.
	 * @param filter
	 *            is a {@link Filter} to be applied to all found files to
	 *            recognize resource files.
	 */
	public static void addResources(JavaArchive javaArchive, File directory,
			Filter<File> filter) {
		for (File entry : ResourceUtils.findResourcesRecursively(directory,
				filter)) {
			javaArchive.addAsResource(entry,
					directory.toURI().relativize(entry.toURI()).getPath());
		}
	}

	/**
	 * This method is used to add resource files to a {@link JavaArchive}. The
	 * resource files are searched within a directory provided as parameter.
	 * 
	 * As filter {@link DefaultResourceFilter} is applied.
	 * 
	 * @param javaArchive
	 *            is the {@link JavaArchive} where the found resource files are
	 *            put into.
	 * @param directory
	 *            is the directory to be searched for suitable resource files.
	 */
	public static void addResources(JavaArchive jar, File directory) {
		addResources(jar, directory, new DefaultResourceFilter());
	}

	/**
	 * This method searches a given directory recursively for resource files.
	 * 
	 * {@link DefaultResourceFilter} is used as default filter.
	 * 
	 * @param directory
	 *            is the directory to be searched for resource files.
	 * @return A list of {@link File} is returned containing all as resource
	 *         recognized files.
	 */
	static List<File> findResourcesRecursively(File directory) {
		return findResourcesRecursively(directory, new DefaultResourceFilter());
	}

	/**
	 * This method searches a given directory recursively for resource files.
	 * 
	 * @param directory
	 *            is the directory to be searched for resource files.
	 * @param filter
	 *            is a {@link Filter} to be applied to all found files to
	 *            recognize resource files.
	 * @return A list of {@link File} is returned containing all as resource
	 *         recognized files.
	 */
	static List<File> findResourcesRecursively(File directory,
			Filter<File> filter) {
		List<File> resources = new ArrayList<File>();
		@SuppressWarnings("unchecked")
		Collection<File> files = FileUtils.listFiles(directory, null, true);
		for (File file : files) {
			if (filter == null || filter.include(file)) {
				resources.add(file);
			}
		}
		return resources;
	}

}
