package com.puresol.purifinity.client.common.evaluation.metrics.cocomo;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.evaluation.controls.EvaluatorGUIFactory;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;

public class CoCoMoEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedSourceCode) {
		return new CoCoMoFileResultComponent(parent, analyzedSourceCode);
	}

}
