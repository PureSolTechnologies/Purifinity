package com.puresol.coding.evaluator.metric;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MetricsEvaluatorTest extends TestCase {

	@Test
	public void testFortran() {
		ProjectAnalyser projectAnalyser = new ProjectAnalyser(new File("test"),
				"com/puresol/coding/lang/fortran/samples/zgerc.f");
		projectAnalyser.run();
		List<File> files = projectAnalyser.getFiles();
		Assert.assertEquals(1, files.size());
		List<CodeRange> codeRanges = projectAnalyser.getAnalyser(files.get(0))
				.getNamedCodeRanges();
		Assert.assertNotNull(codeRanges);
		MetricsEvaluator evaluator = new MetricsEvaluator(projectAnalyser);
		evaluator.run();
		files = evaluator.getFiles();
		Assert.assertEquals(1, files.size());
		codeRanges = evaluator.getCodeRanges(files.get(0));
		Assert.assertNotNull(codeRanges);
	}
}
