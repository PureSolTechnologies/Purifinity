/***************************************************************************
 *
 *   AnalyserFactoryTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.puresol.coding.fortran.FortranAnalyser;
import com.puresol.coding.java.JavaAnalyser;

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
	    Analyser analyser =
		    AnalyserFactory
			    .createAnalyser(
				    new File("test"),
				    new File(
					    "com/puresol/coding/java/JavaAnalyserTest.java"));
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
	    Analyser analyser =
		    AnalyserFactory
			    .createAnalyser(
				    new File("test"),
				    new File(
					    "com/puresol/coding/fortran/FortranTest.f"));
	    Assert
		    .assertEquals(FortranAnalyser.class, analyser
			    .getClass());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    Assert.fail();
	} catch (LanguageNotSupportedException e) {
	    e.printStackTrace();
	    Assert.fail();
	}
    }
}
