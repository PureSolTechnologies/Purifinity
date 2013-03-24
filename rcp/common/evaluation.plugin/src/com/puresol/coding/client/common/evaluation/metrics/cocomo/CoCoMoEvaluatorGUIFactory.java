package com.puresol.coding.client.common.evaluation.metrics.cocomo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.client.common.evaluation.controls.EvaluatorGUIFactory;

public class CoCoMoEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedSourceCode) {
		return new CoCoMoFileResultComponent(parent, SWT.NONE,
				analyzedSourceCode);
	}

}
