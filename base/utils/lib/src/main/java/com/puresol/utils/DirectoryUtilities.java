package com.puresol.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class DirectoryUtilities {

    public static boolean checkAndCreateDirectory(File directory) {
	if (!directory.exists()) {
	    if (!directory.mkdir()) {
		return false;
	    }
	}
	return true;
    }

    public static boolean deleteDirectoryRecursivly(File directory) {
	for (String entryName : directory.list()) {
	    File entry = new File(directory, entryName);
	    if (entry.isDirectory()) {
		if (!deleteDirectoryRecursivly(entry)) {
		    return false;
		}
	    } else {
		if (!entry.delete()) {
		    return false;
		}
	    }
	}
	if (!directory.delete()) {
	    return false;
	}
	return true;
    }

    public static File getExecutionDirectory() {
	return new File(System.getProperty("user.dir", ".").toString());
    }

    public static File getInstallationDirectory(Class<?> clazz,
	    boolean findRootOfPackage) {
	String installDir = System.getProperty("app.installdir");
	if ((installDir != null) && (!installDir.isEmpty())) {
	    return new File(installDir);
	}
	File installDirectory = findInstallationDirectory(clazz,
		findRootOfPackage);
	if (installDirectory != null) {
	    System.setProperty("app.installdir", installDirectory.toString());
	}
	return installDirectory;
    }

    private static File findInstallationDirectory(Class<?> clazz,
	    boolean findRootOfPackage) {
	// get the resource url and path. Closest thing we have to physical
	// location
	URL url = clazz.getResource(clazz.getSimpleName() + ".class");
	if (url.getProtocol().equals("jar")) {
	    // removes the 'jar:' in front
	    String urlPath = url.getPath();
	    // removes 'file:' in front
	    urlPath = urlPath.substring(5);
	    // remove everything behind the '!'
	    urlPath = urlPath.substring(0, urlPath.indexOf("!"));
	    File homeLocation = new File(urlPath).getParentFile();
	    return homeLocation;
	} else if (url.getProtocol().equals("file")) {
	    String urlPath = url.getPath();
	    // decode it and get the parent
	    File homeLocation;
	    try {
		homeLocation = new File(URLDecoder.decode(urlPath, "UTF-8"))
			.getParentFile();
	    } catch (UnsupportedEncodingException e) {
		homeLocation = new File(urlPath, "UTF-8").getParentFile();
	    }
	    // if we should unwind the package to the classpath root.
	    if (!findRootOfPackage) {
		return homeLocation;
	    }
	    // get the package
	    Package p = clazz.getPackage();
	    if (p == null) {
		return null;
	    }
	    /*
	     * work your way up as many times as the target class has packages
	     * so package com.foo.bar will move up 3 levels in the directory
	     */
	    String[] arrs = p.getName().split("[.]");
	    // kill the stuff after the end of the jar file name
	    for (int i = 0; i < arrs.length; i++) {
		homeLocation = homeLocation.getParentFile();
	    }
	    return homeLocation;
	} else {
	    return null;
	}
    }

    public static File getTempDirectory() {
	return new File(System.getProperty("java.io.tmpdir"));
    }

    public static File getUserDirectory() {
	return new File(System.getProperty("user.home"));
    }
}
