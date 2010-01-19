package com.puresol.coding;

import java.io.File;

import org.junit.Test;

import junit.framework.TestCase;

public class CodeDuplicationSearchTest extends TestCase {

	@Test
	public void testOnDummyClass() {
		ProjectAnalyser projectAnalyser = new ProjectAnalyser(new File(
				"test/com/puresol/coding"),
				"DummyClassForCodeDuplicationSearch.java");
		projectAnalyser.update();
		CodeDuplicationSearch search = new CodeDuplicationSearch(
				projectAnalyser);
		search.scan();
	}
}
