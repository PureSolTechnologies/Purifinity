/***************************************************************************
 *
 *   AnalyserFactoryTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.AnalyserFactory;
import com.puresol.coding.lang.LanguageNotSupportedException;
import com.puresol.coding.lang.fortran.FortranAnalyser;
import com.puresol.coding.lang.java.JavaAnalyser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AnalyserFactoryTest extends TestCase {

	@Test
	public void testFileNotFound() {
		try {
			AnalyserFactory.createAnalyser(new File("test"), new File(
					"FileNotPresent.java"));
			Assert.fail("FileNotFoundExceptionWasExpected!");
		} catch (FileNotFoundException e) {
			// nothing to catch, this is expected!
		} catch (LanguageNotSupportedException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testJava() {
		try {
			Analyser analyser = AnalyserFactory.createAnalyser(
					new File("test"), new File(
							"com/puresol/coding/lang/java/JavaParserTest.java"));
			Assert.assertEquals(JavaAnalyser.class, analyser.getClass());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (LanguageNotSupportedException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testFortran() {
		try {
			Analyser analyser = AnalyserFactory
					.createAnalyser(new File("test"), new File(
							"com/puresol/coding/lang/fortran/samples/FortranTest.f"));
			Assert.assertEquals(FortranAnalyser.class, analyser.getClass());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (LanguageNotSupportedException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
