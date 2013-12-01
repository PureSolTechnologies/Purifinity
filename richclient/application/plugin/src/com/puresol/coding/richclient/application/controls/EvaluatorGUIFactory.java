package com.puresol.coding.richclient.application.controls;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.coding.analysis.api.AnalyzedCode;
import com.puresoltechnologies.coding.analysis.api.HashIdFileTree;

/**
 * This interface is used to register factories which produce file result
 * viewers for evaluators.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorGUIFactory {

	/**
	 * This method creates a new ResultComponent for
	 * 
	 * @param parent
	 *            is the parent composite.
	 * @param analysisRun
	 *            is the analysis run to be shown.
	 * @param analyzedFile
	 *            is the file which result is to be shown.
	 * @return
	 */
	public Composite createFileResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedSourceCode);

	public Composite createDirectoryResultComponent(Composite parent,
			AnalysisRun analysisRun, HashIdFileTree directory);

}
