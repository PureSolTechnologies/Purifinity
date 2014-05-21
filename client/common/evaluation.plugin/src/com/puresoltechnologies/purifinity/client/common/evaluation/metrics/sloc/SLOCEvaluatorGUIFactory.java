package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.sloc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.EvaluatorGUIFactory;

public class SLOCEvaluatorGUIFactory implements EvaluatorGUIFactory {

	@Override
	public Composite createResultComponent(Composite parent,
			AnalysisRun analysisRun, AnalysisInformation analyzedCode) {
		return new SLOCResultComponent(parent, SWT.NONE, analysisRun,
				analyzedCode);
	}

}
