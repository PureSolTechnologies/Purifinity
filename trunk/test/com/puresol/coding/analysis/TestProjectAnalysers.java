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

	public static final ProjectAnalyser MINIMAL_PROJECT_ANALYSER = new ProjectAnalyser(
			new File("test/com/puresol/coding/analysis"),
			"TestProjectAnalysers.java");
	static {
		MINIMAL_PROJECT_ANALYSER.run();
		Assert.assertEquals(1, MINIMAL_PROJECT_ANALYSER.getFiles().size());
		Assert
				.assertEquals(0, MINIMAL_PROJECT_ANALYSER.getFailedFiles()
						.size());
	}

}
