package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CopyAndPasteScannerTest extends TestCase {

    @Test
    public void testScanner() {
	ProjectAnalyser projectAnalyser =
		new ProjectAnalyser(new File("test/com/puresol/coding"),
			"DummyClassForDuplicationScannerTest.java");
	projectAnalyser.run();
	Assert.assertEquals(6, projectAnalyser.getCodeRanges(
		new File("/DummyClassForDuplicationScannerTest.java"))
		.size());
	CopyAndPasteScanner search =
		new CopyAndPasteScanner(projectAnalyser);
	search.run();
	ArrayList<Duplication> duplications = search.getDuplications();
	Assert.assertEquals(1, duplications.size());
    }
}
