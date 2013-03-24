package com.puresol.coding.client.common.evaluation.metrics.sloc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.client.common.evaluation.controls.EvaluatorGUIFactory;

public class SLOCEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedCode) {
		return new SLOCResultComponent(parent, SWT.NONE, analysisRun,
				analyzedCode);
	}

}
