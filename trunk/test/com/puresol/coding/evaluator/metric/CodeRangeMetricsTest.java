package com.puresol.coding.evaluator.metric;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.EvaluatorManager;
import com.puresol.coding.lang.fortran.FortranAnalyser;

public class CodeRangeMetricsTest extends TestCase {

	@Test
	public void testFortranMetrics() {
		FortranAnalyser analyser = new FortranAnalyser(new File("test"),
				new File("com/puresol/coding/lang/fortran/samples/zgerc.f"));
		Assert.assertTrue(analyser.getCodeRanges().size() > 1);
		for (CodeRange codeRange : analyser.getCodeRanges()) {
			CodeRangeMetrics metrics = new CodeRangeMetrics(codeRange);
			Assert.assertTrue(metrics.getCalculatedMetrics().size() > 0);
			Assert.assertEquals(EvaluatorManager.getInstance()
					.getMetricClasses().size(), metrics.getCalculatedMetrics()
					.size());
		}
	}

}
