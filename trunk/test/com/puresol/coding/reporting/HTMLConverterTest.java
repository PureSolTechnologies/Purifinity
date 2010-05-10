package com.puresol.coding.reporting;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.lang.java.JavaAnalyser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HTMLConverterTest extends TestCase {

	@Test
	public void testConvertCodeRangeToHTML() {
		try {
			JavaAnalyser analyser = new JavaAnalyser(
					new File("test"),
					new File(
							"com/puresol/coding/reporting/HTMLConverterTest.java"));
			analyser.parse();
			System.out.println(HTMLConverter.convertCodeRangeToHTML(analyser
					.getRootCodeRange()));
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

}
