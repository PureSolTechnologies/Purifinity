package com.puresol.utils;

import java.io.File;

import junit.framework.TestCase;

import org.junit.Test;

public class DirectoryUtilitiesTest extends TestCase {

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
		assertTrue(installationDirectory.toString().endsWith(
				"/bin/"
						+ DirectoryUtilitiesTest.class.getPackage().getName()
								.replaceAll("\\.", "/")));

		installationDirectory = DirectoryUtilities.getInstallationDirectory(
				DirectoryUtilitiesTest.class, true);
		assertTrue(installationDirectory.isDirectory());
		System.out.println(installationDirectory);
		assertTrue(installationDirectory.toString().endsWith(
				"/bin/"
						+ DirectoryUtilitiesTest.class.getPackage().getName()
								.replaceAll("\\.", "/")));
	}

}
