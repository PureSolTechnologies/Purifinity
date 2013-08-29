package com.puresol.purifinity.client.common.evaluation.metrics.cocomo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoDirectoryResults;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoEvaluator;
import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoFileResults;

public class CoCoMoView extends AbstractEvaluationView {

	private CoCoMoResultComponent resultComponent = null;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		resultComponent = new CoCoMoResultComponent(parent, SWT.NONE);
		super.createPartControl(parent);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		EvaluatorStoreFactory evaluationStoreFactory = EvaluatorStoreFactory
				.getFactory();
		EvaluatorStore evaluationStore = evaluationStoreFactory
				.createInstance(CoCoMoEvaluator.class);
		if (path.isFile()) {
			MetricFileResults fileResults = evaluationStore
					.readFileResults(path.getHashId());
			CoCoMoFileResults cocomoResults = (CoCoMoFileResults) fileResults;
			resultComponent.setResults(cocomoResults);
		} else {
			MetricDirectoryResults directoryResults = evaluationStore
					.readDirectoryResults(path.getHashId());
			CoCoMoDirectoryResults cocomoResults = (CoCoMoDirectoryResults) directoryResults;
			resultComponent.setResults(cocomoResults);
		}
		resultComponent.setVisible(true);
	}

	@Override
	protected void updateEvaluation() {
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			showEvaluation(analysisSelection.getHashIdFile());
		}
	}

	@Override
	public void setFocus() {
		resultComponent.setFocus();
	}

}
