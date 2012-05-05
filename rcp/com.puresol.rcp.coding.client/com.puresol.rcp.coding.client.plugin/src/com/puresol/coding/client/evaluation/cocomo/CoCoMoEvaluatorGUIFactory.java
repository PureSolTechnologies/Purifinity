package com.puresol.coding.client.evaluation.cocomo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.controls.EvaluatorGUIFactory;

public class CoCoMoEvaluatorGUIFactory implements EvaluatorGUIFactory {

    @Override
    public Composite createFileResultComponent(Composite parent,
	    AnalysisRun analysisRun, AnalyzedFile analyzedFile) {
	return new CoCoMoFileResultComponent(parent, SWT.NONE, analyzedFile);
    }

    @Override
    public Composite createDirectoryResultComponent(Composite parent,
	    AnalysisRun analysisRun, HashIdFileTree directory) {
	return new CoCoMoDirectoryResultComponent(parent, SWT.NONE, directory);
    }

}
