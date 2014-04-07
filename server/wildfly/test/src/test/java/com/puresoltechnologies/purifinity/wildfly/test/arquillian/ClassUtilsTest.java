package com.puresoltechnologies.purifinity.wildfly.test.arquillian;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.purifinity.wildfly.test.arquillian.ClassUtils;

public class ClassUtilsTest {

	private static final String TARGET_CLASSES_DIRECTORY = "target/classes";

	private static File targetDirectory;

	@BeforeClass
	public static void initialize() {
		targetDirectory = new File(TARGET_CLASSES_DIRECTORY);
		assertTrue("Directory '" + TARGET_CLASSES_DIRECTORY
				+ "' was not found!", targetDirectory.exists());
	}

	/**
	 * @throws ClassNotFoundException
	 *             is thrown when a class file was not found during the search
	 *             of the directory.
	 */
	@Test
	public void testFindClassesRecursivelyWithoutBasePackage()
			throws ClassNotFoundException {
		List<Class<?>> classes = ClassUtils.findClassesRecursively(
				targetDirectory, null);
		assertNotNull(classes);
	}

	/**
	 * @throws ClassNotFoundException
	 *             is thrown when a class file was not found during the search
	 *             of the directory.
	 */
	@Test
	public void testFindClassesRecursivelyWithBasePackage()
			throws ClassNotFoundException {
		List<Class<?>> classes = ClassUtils.findClassesRecursively(new File(
				targetDirectory, "com/puresoltechnologies"),
				"com.puresoltechnologies");
		assertNotNull(classes);
	}

	/**
	 * This tests checks some valid notations for directories and package names
	 * for correct behavior.
	 * 
	 * @throws ClassNotFoundException
	 *             is thrown when a class file was not found during the search
	 *             of the directory.
	 */
	@Test
	public void testFindClassesWithDifferentValidNotations()
			throws ClassNotFoundException {
		// try with additional point at end
		ClassUtils.findClassesRecursively(new File(targetDirectory,
				"com/puresoltechnologies"), "com.puresoltechnologies.");

		// try different slashes
		ClassUtils.findClassesRecursively(new File(targetDirectory,
				"com/puresoltechnologies/"), "com.puresoltechnologies");
		ClassUtils.findClassesRecursively(new File(targetDirectory,
				"/com/puresoltechnologies"), "com.puresoltechnologies");
		ClassUtils.findClassesRecursively(new File(targetDirectory,
				"/com/puresoltechnologies/"), "com.puresoltechnologies");
	}
}
