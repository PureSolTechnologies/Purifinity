package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.duplication.Duplication;
import com.puresol.coding.evaluator.duplication.DuplicationScanner;

import junit.framework.TestCase;

public class DuplicationScannerTest extends TestCase {

	private static final File WORKSPACE_DIRECTORY = new File(
			DuplicationScannerTest.class.getSimpleName() + "workspace");

	@Test
	public void testOnDummyClass() {
		ProjectAnalyzer projectAnalyser = ProjectAnalyzer
				.create(new File(
						"test/com/puresol/coding/analysis/evaluator/duplication/DummyClassForDuplicationScannerTest.java"),
						WORKSPACE_DIRECTORY);
		projectAnalyser.run();
		DuplicationScanner search = new DuplicationScanner(projectAnalyser);
		search.run();
		List<Duplication> duplications = search.getDuplications();
		for (Duplication duplication : duplications) {
			System.out.println(duplication.getLeft().toString());
			System.out.println(duplication.getRight().toString());
		}
		WORKSPACE_DIRECTORY.delete();
	}
}
