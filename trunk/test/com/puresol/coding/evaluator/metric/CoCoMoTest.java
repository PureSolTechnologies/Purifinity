package com.puresol.coding.evaluator.metric;

import java.io.File;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.analysis.TestProjectAnalysers;

public class CoCoMoTest extends TestCase {

	@Test
	public void testCoCoMo() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		ProjectAnalyser pa = TestProjectAnalysers.MINIMAL_PROJECT_ANALYSER;
		Assert.assertEquals(1, pa.getFiles().size());
		Assert.assertEquals(0, pa.getFailedFiles().size());
		CoCoMo cocomo = new CoCoMo(pa);
		cocomo.run();
		List<File> files = cocomo.getFiles();
		Assert.assertEquals(1, files.size());
		List<CodeRange> codeRanges = cocomo.getCodeRanges(cocomo.getFiles()
				.get(0));
		Assert.assertEquals(1, codeRanges.size());
	}
}
