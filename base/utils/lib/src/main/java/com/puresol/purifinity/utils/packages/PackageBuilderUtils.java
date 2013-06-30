package com.puresol.purifinity.utils.packages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackageBuilderUtils {

    private static final Logger logger = LoggerFactory
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
	    File file, Object object) throws IOException {
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		new FileOutputStream(new File(
			packageDirectory.getDirectoryName(), file.toString())));
	try {
	    objectOutputStream.writeObject(object);
	} finally {
	    objectOutputStream.close();
	}
    }
}
