package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoFileResults;

public class BasicCoCoMoView extends AbstractEvaluationView {

	private BasicCoCoMoResultComponent resultComponent = null;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent,
				SWT.H_SCROLL | SWT.V_SCROLL);
		resultComponent = new BasicCoCoMoResultComponent(scrolledComposite);
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
				.createInstance(BasicCoCoMoEvaluator.class);
		if (path.isFile()) {
			MetricFileResults fileResults = evaluationStore
					.readFileResults(path.getHashId());
			BasicCoCoMoFileResults cocomoResults = (BasicCoCoMoFileResults) fileResults;
			resultComponent.setResults(cocomoResults);
		} else {
			MetricDirectoryResults directoryResults = evaluationStore
					.readDirectoryResults(path.getHashId());
			BasicCoCoMoDirectoryResults cocomoResults = (BasicCoCoMoDirectoryResults) directoryResults;
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
