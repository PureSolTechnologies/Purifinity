package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.duplication.CopyAndPasteScanner;
import com.puresol.coding.evaluator.duplication.Duplication;
import com.puresol.utils.Files;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CopyAndPasteScannerTest extends TestCase {

	@Test
	public void testScanner() {
		ProjectAnalyser projectAnalyser = new ProjectAnalyser(new File("test"),
				Files.classToRelativePackagePath(
						DummyClassForDuplicationScannerTest.class).toString());
		projectAnalyser.run();
		CopyAndPasteScanner search = new CopyAndPasteScanner(projectAnalyser);
		search.run();
		ArrayList<Duplication> duplications = search.getDuplications();
		Assert.assertEquals(1, duplications.size());
	}
}
