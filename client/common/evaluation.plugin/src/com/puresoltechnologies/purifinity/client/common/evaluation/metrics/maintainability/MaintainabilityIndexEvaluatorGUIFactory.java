package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.EvaluatorGUIFactory;

public class MaintainabilityIndexEvaluatorGUIFactory implements
		EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalyzedCode analyzedCode) {
		return new MaintainabilityIndexFileResultComponent(parent, SWT.NONE,
				analyzedCode);
	}

}
