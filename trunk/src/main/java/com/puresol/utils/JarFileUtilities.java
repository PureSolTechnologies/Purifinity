package com.puresol.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * This class provides some utilities for handling jar files and their content.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JarFileUtilities {

    private static final Logger logger = Logger.getLogger(JarFileUtilities.class);

    public static boolean extractResource(URL resource, File destination) {
	try {
	    File parent = destination.getParentFile();
	    if (parent != null) {
		if (!parent.exists()) {
		    parent.mkdirs();
		}
	    }
	    InputStream inStream = resource.openStream();
	    FileOutputStream outStream = new FileOutputStream(destination);
	    byte[] buffer = new byte[1024];
	    while (inStream.read(buffer) >= 0) {
		outStream.write(buffer);
	    }
	    outStream.close();
	    inStream.close();
	    return true;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }
}
