package com.puresol.jars;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.puresol.exceptions.StrangeSituationException;

/**
 * The JarLoader class is for dynamic loading and initializing of jar libraries
 * at startup. The JarLoader does not support logging due to the uncoupling of
 * extern logging facitlies. Loading should be performed in that way only at
 * startup.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JarLoader {

    private static class JarFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String file) {
	    return file.endsWith(".jar");
	}

    }

    public static void loadJarsFromDirectory(File dir, boolean verbose) {
	if (verbose) {
	    System.out.println("Loading all needed libraries...");
	}
	String[] files = dir.list(new JarFileFilter());
	int counter = 0;
	for (String file : files) {
	    counter++;
	    int percent = (int) Math.round(10.0 * (float) counter
		    / (float) files.length);
	    System.out.print("[");
	    for (int dummy = 1; dummy <= 10; dummy++) {
		if (dummy <= percent) {
		    System.out.print("*");
		} else {
		    System.out.print(".");
		}
	    }
	    if (verbose) {
		System.out.print("] Load: " + file + "\r");
	    }
	    JarLoader.load(new File("lib/" + file));
	}
	System.out.println();
    }

    /**
     * @param args
     */
    public static void load(File jarFile) {
	try {
	    URL jarURL = jarFile.toURI().toURL();
	    /*
	     * Either do it this way:
	     * 
	     * ClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
	     * 
	     * or the other:
	     */
	    ClassLoader classLoader = Thread.currentThread()
		    .getContextClassLoader();
	    if (classLoader != null && (classLoader instanceof URLClassLoader)) {
		URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
		Method addURL = URLClassLoader.class.getDeclaredMethod(
			"addURL", new Class[] { URL.class });
		addURL.setAccessible(true);
		addURL.invoke(urlClassLoader, new Object[] { jarURL });
	    } else {
		throw new StrangeSituationException(
			"The ContextClassLoader should be a URLClassLoader!");
	    }
	    return;
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (SecurityException e) {
	    e.printStackTrace();
	} catch (NoSuchMethodException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (InvocationTargetException e) {
	    e.printStackTrace();
	}
	throw new StrangeSituationException("Could not load jar file!");
    }
}
