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

	// @Test
	// public void testRandomNumbers() {
	// try {
	// JavaAnalyser analyser = new JavaAnalyser(new File("src"), Files
	// .classToRelativePackagePath(CodeAnalysis.class));
	// analyser.parse();
	// } catch (AnalyserException e) {
	// e.printStackTrace();
	// Assert.fail("No exception was expected!");
	// }
	// }

	// @Test
	// public void testTranslators() {
	// Logger.getRootLogger().setLevel(Level.DEBUG);
	// try {
	// JavaAnalyser analyser = new JavaAnalyser(new File("test"), Files
	// .classToRelativePackagePath(Translator.class));
	// analyser.parse();
	// } catch (AnalyserException e) {
	// e.printStackTrace();
	// Assert.fail("No exception was expected!");
	// }
	// }

	// @Test
	// public void testSourceLocation() {
	// Logger.getRootLogger().setLevel(Level.DEBUG);
	// try {
	// JavaAnalyser analyser = new JavaAnalyser(new File("test"), Files
	// .classToRelativePackagePath(SourceLocation.class));
	// analyser.parse();
	// } catch (AnalyserException e) {
	// e.printStackTrace();
	// Assert.fail("No exception was expected!");
	// }
	// }

	// @Test
	// public void testI18NRelease() {
	// Logger.getRootLogger().setLevel(Level.DEBUG);
	// try {
	// JavaAnalyser analyser = new JavaAnalyser(new File("test"), Files
	// .classToRelativePackagePath(I18NRelease.class));
	// analyser.parse();
	// } catch (AnalyserException e) {
	// e.printStackTrace();
	// Assert.fail("No exception was expected!");
	// }
	// }

	@Test
	public void testI18NJavaParser() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File("test"), FileUtilities
					.classToRelativePackagePath(I18NJavaParser.class));
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
