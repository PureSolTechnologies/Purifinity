package com.puresol.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;

public class IntermediateCoCoMoView extends AbstractEvaluationView {

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
	}

	@Override
	protected void updateEvaluation() {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			showEvaluation(analysisSelection.getFileTreeNode());
		}
	}

	@Override
	public void setFocus() {
	}

}
