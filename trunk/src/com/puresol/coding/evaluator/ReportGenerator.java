package com.puresol.coding.evaluator;

import java.io.File;

/**
 * This interface defines a ReportGenerator for source code projects. The
 * differrent implementations do support different output formats like Text,
 * HTML, XML....
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ReportGenerator {

	/**
	 * This method returns the part for the report where the project summary is
	 * reported.
	 */
	public void createProject(File outputDirectory);

}
