package com.puresol.coding.analysis;

import java.io.File;

import junit.framework.Assert;

/**
 * This class provides some ProjectAnalysers for Testing purposes in static
 * final fields for CPU time saving access.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestProjectAnalysers {

	private static final File WORKSPACE_DIRECTORY = new File(
			TestProjectAnalysers.class.getSimpleName() + "workspace");
	public static final ProjectAnalyzer MINIMAL_PROJECT_ANALYSER = ProjectAnalyzer
			.create(new File(
					"test/com/puresol/coding/analysis/TestProjectAnalysers.java"),
					WORKSPACE_DIRECTORY);
	static {
		MINIMAL_PROJECT_ANALYSER.run();
		Assert.assertEquals(1, MINIMAL_PROJECT_ANALYSER.getFiles().size());
		Assert.assertEquals(0, MINIMAL_PROJECT_ANALYSER.getFailedFiles().size());
		WORKSPACE_DIRECTORY.delete();
	}

}
