package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.duplication.CopyAndPasteScanner;
import com.puresol.coding.evaluator.duplication.Duplication;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CopyAndPasteScannerTest extends TestCase {

	@Test
	public void testScanner() {
		ProjectAnalyser projectAnalyser = new ProjectAnalyser(new File("test"),
				FileUtilities.classToRelativePackagePath(
						DummyClassForDuplicationScannerTest.class).toString());
		projectAnalyser.run();
		CopyAndPasteScanner search = new CopyAndPasteScanner(projectAnalyser);
		search.run();
		List<Duplication> duplications = search.getDuplications();
		Assert.assertEquals(1, duplications.size());
	}
}
