/***************************************************************************
 *
 *   ExtentedClassLoader.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.introspect;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ExtentedClassLoader extends URLClassLoader {

	public ExtentedClassLoader(URL[] urls) {
		super(urls);
	}

	public void addFile(File file) throws MalformedURLException {
		String urlPath = "jar:file://" + file.getAbsolutePath() + "!/";
		addURL(new URL(urlPath));
	}

	public void addFile(String path) throws MalformedURLException {
		String urlPath = "jar:file://" + path + "!/";
		addURL(new URL(urlPath));
	}
}
