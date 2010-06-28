package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.duplication.CopyAndPasteScanner;
import com.puresol.coding.evaluator.duplication.Duplication;
import com.puresol.utils.FileUtilities;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CopyAndPasteScannerTest extends TestCase {

	private static final File WORKSPACE_DIRECTORY = new File(
			CopyAndPasteScannerTest.class.getSimpleName() + "workspace");

	@Test
	public void testScanner() {
		ProjectAnalyzer projectAnalyser = ProjectAnalyzer.create(
				new File("test", FileUtilities.classToRelativePackagePath(
						DummyClassForDuplicationScannerTest.class).toString()),
				WORKSPACE_DIRECTORY);
		projectAnalyser.run();
		CopyAndPasteScanner search = new CopyAndPasteScanner(projectAnalyser);
		search.run();
		List<Duplication> duplications = search.getDuplications();
		Assert.assertEquals(1, duplications.size());
		WORKSPACE_DIRECTORY.delete();
	}
}
