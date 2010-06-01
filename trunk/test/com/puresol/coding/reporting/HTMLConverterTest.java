package com.puresol.coding.reporting;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.lang.java.JavaAnalyser;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HTMLConverterTest extends TestCase {

    @Test
    public void testConvertCodeRangeToHTML() {
	try {
	    Logger.getRootLogger().setLevel(Level.DEBUG);
	    JavaAnalyser analyser = new JavaAnalyser(new File("test"), FileUtilities
		    .classToRelativePackagePath(HTMLConverterTest.class));
	    analyser.parse();
	    System.out.println(HTMLConverter.convertCodeRangeToHTML(analyser
		    .getRootCodeRange()));
	} catch (AnalyserException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }

}
