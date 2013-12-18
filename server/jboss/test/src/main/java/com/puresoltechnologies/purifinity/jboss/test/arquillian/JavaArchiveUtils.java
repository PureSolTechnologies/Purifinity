package com.puresoltechnologies.purifinity.jboss.test.arquillian;

import java.io.File;
import java.util.List;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * This class provides some utilities to edit and handle {@link JavaArchive}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class JavaArchiveUtils {

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private JavaArchiveUtils() {
	}

	/**
	 * Creates a JAR archive form a given directory. The directory is searched
	 * and the found files are added.
	 * 
	 * @param directory
	 *            is the directory to be searched for the {@link JavaArchive}
	 *            content.
	 * @param archiveFilename
	 *            is the name of the archive to be created.
	 * @return A {@link JavaArchive} is returned containing the content of the
	 *         directory.
	 * @throws ClassNotFoundException
	 *             is thrown if a class file was found which is not part of the
	 *             CLASSPATH.
	 */
	public static JavaArchive createJavaArchiveFromDirectory(File directory,
			String archiveFilename) throws ClassNotFoundException {
		if ((!directory.isDirectory()) || (!directory.exists())) {
			throw new IllegalArgumentException(
					"The provided directory needs to be a directory, present and accessible!");
		}
		if (archiveFilename == null) {
			throw new IllegalArgumentException(
					"A JAR file name has to be provided!");
		}

		JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class,
				archiveFilename);

		List<Class<?>> classes = ClassUtils.findClassesRecursively(directory,
				null);
		javaArchive.addClasses(classes.toArray(new Class[classes.size()]));

		for (File entry : ResourceUtils.findResourcesRecursively(directory)) {
			javaArchive.addAsResource(entry,
					directory.toURI().relativize(entry.toURI()).getPath());
		}

		return javaArchive;
	}

}
