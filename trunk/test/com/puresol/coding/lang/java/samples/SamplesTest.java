package com.puresol.coding.lang.java.samples;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.lang.java.JavaAnalyser;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SamplesTest extends TestCase {

	@Test
	public void testTranslators() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File(new File("test"),
					FileUtilities.classToRelativePackagePath(Translator.class)
							.toString()));
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testSourceLocation() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File(new File("test"),
					FileUtilities.classToRelativePackagePath(
							SourceLocation.class).toString()));
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testI18NRelease() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File(new File("test"),
					FileUtilities.classToRelativePackagePath(I18NRelease.class)
							.toString()));
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testI18NJavaParser() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File(new File("test"),
					FileUtilities.classToRelativePackagePath(
							I18NJavaParser.class).toString()));
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
