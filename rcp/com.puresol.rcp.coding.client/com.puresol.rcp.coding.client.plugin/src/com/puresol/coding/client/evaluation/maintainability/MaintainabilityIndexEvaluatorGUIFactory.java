package com.puresol.coding.client.evaluation.maintainability;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.controls.EvaluatorGUIFactory;

public class MaintainabilityIndexEvaluatorGUIFactory implements
	EvaluatorGUIFactory {

    @Override
    public Composite createFileResultComponent(Composite parent,
	    AnalysisRun analysisRun, AnalyzedFile analyzedFile) {
	return new MaintainabilityIndexFileResultComponent(parent, SWT.NONE,
		analyzedFile);
    }

    @Override
    public Composite createDirectoryResultComponent(Composite parent,
	    AnalysisRun analysisRun, HashIdFileTree directory) {
	return new MaintainabilityIndexDirectoryResultComponent(parent,
		SWT.NONE, analysisRun, directory);
    }

}