/***************************************************************************
 *
 *   AnalyserFactory.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.LanguageNotSupportedException;
import com.puresol.coding.lang.cpp.CPPAnalyser;
import com.puresol.coding.lang.fortran.FortranAnalyser;
import com.puresol.coding.lang.java.JavaAnalyser;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Files;
import com.puresol.utils.Instances;
import com.puresol.utils.MethodInvokationException;

/**
 * This factory creates an Analyser class for a given File in dependence
 * for its implementation language.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyserFactory {

    private static final Logger logger =
	    Logger.getLogger(AnalyserFactory.class);

    private static ArrayList<Class<? extends Analyser>> analysers;
    static {
	analysers = new ArrayList<Class<? extends Analyser>>();
	analysers.add(JavaAnalyser.class);
	analysers.add(CPPAnalyser.class);
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
	if (!Files.addPaths(projectDirectory, file).exists()) {
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

	    if (!Instances.runStaticMethod(clazz, "isSuitable",
		    boolean.class, file)) {
		return null;
	    }
	    return Instances.createInstance(clazz, projectDirectory, file);
	} catch (MethodInvokationException e) {
	    throw new StrangeSituationException(e);
	} catch (ClassInstantiationException e) {
	    throw new StrangeSituationException(e);
	}
    }
}
