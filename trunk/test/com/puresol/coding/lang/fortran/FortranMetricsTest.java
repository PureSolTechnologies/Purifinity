package com.puresol.coding.lang.fortran;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.metric.SLOCMetric;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FortranMetricsTest extends TestCase {

	private static final FortranAnalyser analyser = new FortranAnalyser(
			new File("test"), new File(
					"com/puresol/coding/lang/fortran/samples/zgerc.f"));
	static {
		try {
			analyser.parse();
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testSLOCMetrics() {
		CodeRange fileCodeRange = analyser.getRootCodeRange();
		Assert.assertEquals(CodeRangeType.FILE, fileCodeRange
				.getCodeRangeType());
		SLOCMetric sloc = new SLOCMetric(fileCodeRange);
		Assert.assertEquals(159, sloc.getPhyLOC());
		Assert.assertEquals(62, sloc.getProLOC());
		Assert.assertEquals(97, sloc.getComLOC());
		Assert.assertEquals(0, sloc.getBlLOC());
	}

}
