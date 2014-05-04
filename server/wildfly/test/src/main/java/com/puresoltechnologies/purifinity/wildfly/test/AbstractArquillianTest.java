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
	private static final String EAR_EXTENSION = ".ear";

	protected static File findProjectEARFile() {
		if (!APPLICATION_DIRECTORY.exists()) {
			throw new IllegalStateException(
					"The directory '"
							+ APPLICATION_DIRECTORY
							+ "' does not exist! "
							+ "There is maybe an issue with the project setup or do you need to build with Maven?");
		}
		File[] earFiles = APPLICATION_DIRECTORY
				.listFiles(new FilenameSuffixFilter(EAR_EXTENSION));
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
