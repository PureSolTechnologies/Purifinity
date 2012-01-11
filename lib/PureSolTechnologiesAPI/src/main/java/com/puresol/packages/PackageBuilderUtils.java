package com.puresol.packages;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

public class PackageBuilderUtils {

	private static final Logger logger = Logger
			.getLogger(PackageBuilderUtils.class);

	public static void createPackageDirectory(
			PackageDirectory packageDirectory, File subDirectory) {
		final File directory = new File(packageDirectory.getDirectoryName(),
				subDirectory.getPath());
		logger.info("Checking for presence of directory '" + directory + "'...");
		if (!directory.exists()) {
			logger.info("Directory '" + directory
					+ "' is not existing. Creating...");
			if (!directory.mkdirs()) {
				logger.error("Directory '" + directory
						+ "' could not be created! Abort...");
				return;
			}
		}
		logger.info("done.");
	}

	public static void persistObject(PackageDirectory packageDirectory,
			File file, Object object) throws PersistenceException, IOException {
		Persistence.persist(object,
				new File(packageDirectory.getDirectoryName(), file.toString()));
	}
}
