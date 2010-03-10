package com.puresol.coding.analysis.evaluator.duplication;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.analysis.evaluator.duplication.Duplication;
import com.puresol.coding.analysis.evaluator.duplication.DuplicationScanner;

import junit.framework.TestCase;

public class DuplicationScannerTest extends TestCase {

	@Test
	public void testOnDummyClass() {
		ProjectAnalyser projectAnalyser = new ProjectAnalyser(new File(
				"test/com/puresol/coding/analysis/evaluator/duplication"),
				"DummyClassForDuplicationScannerTest.java");
		projectAnalyser.run();
		DuplicationScanner search = new DuplicationScanner(projectAnalyser);
		search.run();
		ArrayList<Duplication> duplications = search.getDuplications();
		for (Duplication duplication : duplications) {
			System.out.println(duplication.getLeft().toString());
			System.out.println(duplication.getRight().toString());
		}
	}
}
