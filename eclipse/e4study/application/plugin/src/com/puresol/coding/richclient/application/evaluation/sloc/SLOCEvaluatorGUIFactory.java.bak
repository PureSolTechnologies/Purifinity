package com.puresol.coding.richclient.application.evaluation.sloc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.richclient.application.controls.EvaluatorGUIFactory;

public class SLOCEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createFileResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedCode) {
		return new SLOCFileResultComponent(parent, SWT.NONE, analysisRun,
				analyzedCode);
	}

	@Override
	public Composite createDirectoryResultComponent(Composite parent,
			AnalysisRun analysisRun, HashIdFileTree directory) {
		return new SLOCDirectoryResultComponent(parent, SWT.NONE, analysisRun,
				directory);
	}

}
