package com.puresol.coding.evaluator.duplication;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.duplication.Duplication;
import com.puresol.coding.evaluator.duplication.DuplicationScanner;

import junit.framework.TestCase;

public class DuplicationScannerTest extends TestCase {

	@Test
	public void testOnDummyClass() {
		ProjectAnalyser projectAnalyser = new ProjectAnalyser(
				new File(
						"test/com/puresol/coding/analysis/evaluator/duplication/DummyClassForDuplicationScannerTest.java"),
				new File("workspace"));
		projectAnalyser.run();
		DuplicationScanner search = new DuplicationScanner(projectAnalyser);
		search.run();
		List<Duplication> duplications = search.getDuplications();
		for (Duplication duplication : duplications) {
			System.out.println(duplication.getLeft().toString());
			System.out.println(duplication.getRight().toString());
		}
	}
}
