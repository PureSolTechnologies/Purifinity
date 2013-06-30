package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.evaluation.controls.EvaluatorGUIFactory;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;

public class MaintainabilityIndexEvaluatorGUIFactory implements
		EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedCode) {
		return new MaintainabilityIndexFileResultComponent(parent, SWT.NONE,
				analyzedCode);
	}

}
