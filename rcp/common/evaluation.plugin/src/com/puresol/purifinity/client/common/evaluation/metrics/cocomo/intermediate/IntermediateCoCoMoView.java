package com.puresol.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoDirectoryResults;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoEvaluator;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoFileResults;

public class IntermediateCoCoMoView extends AbstractEvaluationView {
	private IntermediateCoCoMoResultComponent resultComponent = null;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent,
				SWT.H_SCROLL | SWT.V_SCROLL);
		resultComponent = new IntermediateCoCoMoResultComponent(
				scrolledComposite);
		scrolledComposite.setContent(resultComponent);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinSize(resultComponent.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));
		super.createPartControl(parent);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		EvaluatorStoreFactory evaluationStoreFactory = EvaluatorStoreFactory
				.getFactory();
		EvaluatorStore evaluationStore = evaluationStoreFactory
				.createInstance(IntermediateCoCoMoEvaluator.class);
		if (path.isFile()) {
			MetricFileResults fileResults = evaluationStore
					.readFileResults(path.getHashId());
			IntermediateCoCoMoFileResults cocomoResults = (IntermediateCoCoMoFileResults) fileResults;
			resultComponent.setResults(cocomoResults);
		} else {
			MetricDirectoryResults directoryResults = evaluationStore
					.readDirectoryResults(path.getHashId());
			IntermediateCoCoMoDirectoryResults cocomoResults = (IntermediateCoCoMoDirectoryResults) directoryResults;
			resultComponent.setResults(cocomoResults);
		}
		resultComponent.setVisible(true);
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
		resultComponent.setFocus();
	}

}