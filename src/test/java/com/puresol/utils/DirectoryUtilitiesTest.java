package com.puresol.utils;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class DirectoryUtilitiesTest {

	@Test
	public void testExecutingDirectory() {
		File executingDirectory = DirectoryUtilities.getExecutionDirectory();
		assertTrue(executingDirectory.isDirectory());
	}

	@Test
	public void testInstallationDirectory() {
		File installationDirectory = DirectoryUtilities
				.getInstallationDirectory(DirectoryUtilitiesTest.class, false);
		assertTrue(installationDirectory.isDirectory());
		System.out.println(installationDirectory);
		String expected = "/target/test-classes/"
				+ DirectoryUtilitiesTest.class.getPackage().getName()
						.replaceAll("\\.", "/");
		System.out.println(expected);
		assertTrue(installationDirectory.toString().endsWith(expected));

		installationDirectory = DirectoryUtilities.getInstallationDirectory(
				DirectoryUtilitiesTest.class, true);
		assertTrue(installationDirectory.isDirectory());
		System.out.println(installationDirectory);
		expected = "/target/test-classes/"
				+ DirectoryUtilitiesTest.class.getPackage().getName()
						.replaceAll("\\.", "/");
		System.out.println(expected);
		assertTrue(installationDirectory.toString().endsWith(expected));
	}

}
