package com.puresol.coding.lang.java.source.grammar;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.lang.java.CompilationUnit;
import com.puresol.coding.lang.java.JavaAnalyser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ClassSkeletonTest extends TestCase {

	@Test
	public void test() {
		try {
			JavaAnalyser analyser = new JavaAnalyser(
					new File("test"),
					new File(
							"com/puresol/coding/lang/java/samples/ClassSkeleton.java.test"));
			analyser.parse();
			CodeRange codeRange = analyser.getRootCodeRange();
			Assert.assertEquals(CompilationUnit.class, codeRange.getClass());
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}
}
