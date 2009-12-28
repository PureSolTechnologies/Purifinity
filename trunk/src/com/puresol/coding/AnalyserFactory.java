package com.puresol.coding;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.coding.fortran.FortranAnalyser;
import com.puresol.coding.java.JavaAnalyser;
import com.puresol.exceptions.StrangeSituationException;

public class AnalyserFactory {

    private static final Logger logger =
	    Logger.getLogger(AnalyserFactory.class);

    private static ArrayList<Class<? extends Analyser>> analysers;
    static {
	analysers = new ArrayList<Class<? extends Analyser>>();
	analysers.add(JavaAnalyser.class);
	analysers.add(FortranAnalyser.class);
    }

    public static Analyser createAnalyser(File projectDirectory, File file)
	    throws LanguageNotSupportedException, FileNotFoundException {
	logger
		.info("Create analyser for file '" + file.getPath()
			+ "'...");
	checkFile(projectDirectory, file);
	return create(projectDirectory, file);
    }

    private static void checkFile(File projectDirectory, File file)
	    throws FileNotFoundException {
	if (!new File(projectDirectory.getPath() + "/" + file.getPath())
		.exists()) {
	    logger.warn("File '" + file.getPath() + "' is not existing!");
	    throw new FileNotFoundException("File '" + file.getPath()
		    + "' is not existing!");
	}
    }

    private static Analyser create(File projectDirectory, File file)
	    throws LanguageNotSupportedException {
	for (Class<? extends Analyser> analyserClass : analysers) {
	    Analyser analyser =
		    checkAndCreate(analyserClass, projectDirectory, file);
	    if (analyser != null) {
		return analyser;
	    }
	}
	logger
		.warn("No analyser for file '" + file.getPath()
			+ "' found!");
	throw new LanguageNotSupportedException(
		"No coding language found for file " + file.getPath());
    }

    private static Analyser checkAndCreate(
	    Class<? extends Analyser> clazz, File projectDirectory,
	    File file) {
	try {
	    Method isSuitable = clazz.getMethod("isSuitable", File.class);
	    if (!(Boolean) isSuitable.invoke(null, file)) {
		return null;
	    }
	    Constructor<?> constructor =
		    clazz.getConstructor(File.class, File.class);
	    return (Analyser) constructor.newInstance(projectDirectory,
		    file);
	} catch (SecurityException e) {
	    throw new StrangeSituationException(e);
	} catch (NoSuchMethodException e) {
	    throw new StrangeSituationException(e);
	} catch (IllegalArgumentException e) {
	    throw new StrangeSituationException(e);
	} catch (IllegalAccessException e) {
	    throw new StrangeSituationException(e);
	} catch (InvocationTargetException e) {
	    throw new StrangeSituationException(e);
	} catch (InstantiationException e) {
	    throw new StrangeSituationException(e);
	}
    }
}
