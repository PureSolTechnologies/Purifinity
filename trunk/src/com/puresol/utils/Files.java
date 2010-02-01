package com.puresol.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.log4j.Logger;

public class Files {

    private static final Logger logger = Logger.getLogger(Files.class);

    public static File addPaths(File path1, File path2) {
	return new File(path1.getPath() + "/" + path2.getPath());
    }

    public static boolean writeFile(File directory, String fileName,
	    String text) {
	RandomAccessFile ra = null;
	try {
	    ra =
		    new RandomAccessFile(new File(directory.toString()
			    + "/" + fileName), "rw");
	    ra.setLength(0);
	    ra.writeBytes(text);
	    ra.close();
	    return true;
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	if (ra != null) {
	    try {
		ra.close();
	    } catch (IOException e) {
	    }
	}
	return false;
    }
}
