package com.puresol.coding.lang.java.source.grammar.interfaces;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.lang.java.JavaParser;
import com.puresol.coding.lang.java.JavaAnalyser;
import com.puresol.coding.lang.java.samples.InterfaceSkeleton;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class InterfaceSkeletonTest extends TestCase {

	@Test
	public void test() {
		try {
			JavaAnalyser analyser = new JavaAnalyser(new File(new File("test"),
					FileUtilities.classToRelativePackagePath(
							InterfaceSkeleton.class).toString()));
			analyser.parse();
			CodeRange codeRange = analyser.getRootCodeRange();
			Assert.assertEquals(JavaParser.class, codeRange.getClass());
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
