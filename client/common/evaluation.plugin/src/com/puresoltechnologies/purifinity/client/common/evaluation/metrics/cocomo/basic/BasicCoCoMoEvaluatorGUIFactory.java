package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.basic;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.EvaluatorGUIFactory;

public class BasicCoCoMoEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedSourceCode) {
		return new BasicCoCoMoFileResultComponent(parent, analyzedSourceCode);
	}

}
