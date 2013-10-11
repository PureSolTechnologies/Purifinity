package com.puresol.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.evaluation.controls.EvaluatorGUIFactory;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;

public class IntermediateCoCoMoEvaluatorGUIFactory implements
		EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedSourceCode) {
		return new IntermediateCoCoMoFileResultComponent(parent,
				analyzedSourceCode);
	}

}
