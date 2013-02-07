package com.puresol.coding.richclient.application.evaluation.cocomo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.richclient.application.controls.EvaluatorGUIFactory;

public class CoCoMoEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createFileResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedSourceCode) {
		return new CoCoMoFileResultComponent(parent, SWT.NONE,
				analyzedSourceCode);
	}

	@Override
	public Composite createDirectoryResultComponent(Composite parent,
			AnalysisRun analysisRun, HashIdFileTree directory) {
		return new CoCoMoDirectoryResultComponent(parent, SWT.NONE, directory);
	}

}
