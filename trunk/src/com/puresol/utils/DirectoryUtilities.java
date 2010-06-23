package com.puresol.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

public class DirectoryUtilities {

	private static final Logger logger = Logger
			.getLogger(DirectoryUtilities.class);

	public static boolean checkAndCreateDirectory(File directory) {
		if (!directory.exists()) {
			if (!directory.mkdir()) {
				return false;
			}
		}
		return true;
	}

	public static File getExecutionDirectory() {
		return new File(System.getProperty("user.dir", ".").toString());
	}

	public static File getInstallationDirectory(Class<?> clazz,
			boolean findRootOfPackage) {
		try {
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
				return new File(urlPath).getParentFile();
			} else if (url.getProtocol().equals("file")) {
				String urlPath = url.getPath();
				// decode it and get the parent
				File homeLocation = new File(URLDecoder
						.decode(urlPath, "UTF-8")).getParentFile();
				// if we should unwind the package to the classpath root.
				if (!findRootOfPackage) {
					return homeLocation;
				}
				// get the package
				Package p = clazz.getPackage();
				if (p == null) {
					return null;
				}
				// work your way up as many times as the target class has
				// packages
				// so package com.foo.bar will move up 3 levels in the
				// directory
				String[] arrs = p.getName().split("[.]");
				// kill the stuff after the end of the jar file name
				for (int i = 0; i < arrs.length; i++) {
					homeLocation = homeLocation.getParentFile();
				}
				return homeLocation;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
