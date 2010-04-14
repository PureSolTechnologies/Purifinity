package com.puresol.coding.reporting;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.lang.java.JavaAnalyser;

import junit.framework.TestCase;

public class HTMLConverterTest extends TestCase {

	@Test
	public void testConvertCodeRangeToHTML() {
		JavaAnalyser analyser = new JavaAnalyser(new File("test"), new File(
				"com/puresol/coding/reporting/HTMLConverterTest.java"));
		List<CodeRange> codeRanges = analyser.getCodeRanges();
		System.out.println(HTMLConverter.convertCodeRangeToHTML(codeRanges
				.get(0)));
	}

}
