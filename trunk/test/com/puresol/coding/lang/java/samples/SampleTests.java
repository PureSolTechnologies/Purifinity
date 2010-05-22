package com.puresol.coding.lang.java.samples;

import java.io.File;

import org.junit.Test;

import apps.CodeAnalysis;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.lang.java.JavaAnalyser;
import com.puresol.utils.Files;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SampleTests extends TestCase {

	@Test
	public void testRandomNumbers() {
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File("src"), Files
					.classToRelativePackagePath(CodeAnalysis.class));
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

}
