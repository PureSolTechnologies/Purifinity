package com.puresol.coding.client.evaluation.sloc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.controls.EvaluatorGUIFactory;

public class SLOCEvaluatorGUIFactory implements EvaluatorGUIFactory {

    @Override
    public Composite createFileResultComponent(Composite parent,
	    AnalysisRun analysisRun, AnalyzedFile analyzedFile) {
	return new SLOCFileResultComponent(parent, SWT.NONE, analysisRun,
		analyzedFile);
    }

    @Override
    public Composite createDirectoryResultComponent(Composite parent,
	    AnalysisRun analysisRun, HashIdFileTree directory) {
	return new SLOCDirectoryResultComponent(parent, SWT.NONE, analysisRun,
		directory);
    }

}
