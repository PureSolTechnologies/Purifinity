package com.puresoltechnologies.purifinity.wildfly.test;

import java.io.File;

/**
 * This class contains all common settings and functionality used for Arquillian
 * client and server tests.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractArquillianTest {

	public static final File APPLICATION_DIRECTORY = new File("../app/target");
	public static final File PLUGIN_DIRECTORY = new File("../plugin/target");
	private static final String EAR_EXTENSION = ".ear";

	protected static File findApplicationEARFile() {
		return findEARFileInDirectory(APPLICATION_DIRECTORY);
	}

	protected static File findPluginEARFile() {
		return findEARFileInDirectory(PLUGIN_DIRECTORY);
	}

	private static File findEARFileInDirectory(File directory) {
		if (!directory.exists()) {
			throw new IllegalStateException(
					"The directory '"
							+ directory
							+ "' does not exist! "
							+ "There is maybe an issue with the project setup or do you need to build with Maven?");
		}
		File[] earFiles = directory.listFiles(new FilenameSuffixFilter(
				EAR_EXTENSION));
		if (earFiles.length == 0) {
			throw new IllegalStateException(
					"Cannot find EAR file! Application needs to be built.");
		}
		if (earFiles.length > 1) {
			throw new IllegalStateException(
					"Multiple EAR files were found! There is only on EAR file allowed. "
							+ "Maybe this is an issue with the project setup.");
		}
		return earFiles[0];
	}
}
