package com.puresol.coding.reporting;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.lang.java.JavaAnalyser;

import junit.framework.TestCase;

public class HTMLConverterTest extends TestCase {

	@Test
	public void testConvertCodeRangeToHTML() {
		JavaAnalyser analyser = new JavaAnalyser(new File("test"), new File(
				"com/puresol/coding/reporting/HTMLConverterTest.java"));
		System.out.println(HTMLConverter.convertCodeRangeToHTML(analyser
				.getRootCodeRange()));
	}

}
