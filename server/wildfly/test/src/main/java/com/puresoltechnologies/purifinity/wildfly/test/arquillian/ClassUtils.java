package com.puresoltechnologies.purifinity.wildfly.test.arquillian;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * This class provides functionality to deal with class files. This class is
 * used in combination with Arquillian tests.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class ClassUtils {

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private ClassUtils() {
	}

	/**
	 * This method searches a directory recursively and loads the classes.
	 * 
	 * @param directory
	 *            is the directory to be searched.
	 * @param packagePrefix
	 *            is a package name part which is part in front of the found
	 *            files paths. This is used to scan only sub directories of a
	 *            class directory structure.
	 * @return List of found classes as {@link Class}.
	 * @throws ClassNotFoundException
	 *             is checked if a class file was found which is not in the
	 *             CLASSPATH. It is an exception due to the returned
	 *             {@link List} contains {@link Class} objects.
	 * 
	 */
	public static List<Class<?>> findClassesRecursively(File directory,
			String packagePrefix) throws ClassNotFoundException {
		if (packagePrefix == null) {
			packagePrefix = "";
		} else if (!packagePrefix.endsWith(".")) {
			packagePrefix += ".";
		}
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		Collection<File> files = FileUtils.listFiles(directory,
				new String[] { "class" }, true);
		String directoryWoBackSlashes = directory.getPath().replaceAll("\\\\",
				"/");
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (File file : files) {
			String fileWoBackSlashes = file.getPath().replaceAll("\\\\", "/");
			String className = packagePrefix
					+ fileWoBackSlashes
							.replace(directoryWoBackSlashes + "/", "")
							.replaceAll("/", ".").replace(".class", "");
			classes.add(systemClassLoader.loadClass(className));
		}
		return classes;
	}
}
