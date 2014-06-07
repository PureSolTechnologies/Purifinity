package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.basic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresoltechnologies.purifinity.client.common.server.connectors.BasicCoCoMoEvaluatorConnector;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

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
	public void showEvaluation(AnalysisFileTree path)
			throws EvaluationStoreException {

		EvaluatorStore evaluationStore = BasicCoCoMoEvaluatorConnector
				.getStore();
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
	protected void clear() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateView() throws EvaluationStoreException {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			showEvaluation(analysisSelection.getFileTreeNode());
		}
	}

	@Override
	public void setFocus() {
		resultComponent.setFocus();
	}

	@Override
	protected boolean hasFullViewSettings() {
		return true;
	}

	@Override
	protected boolean hasChangedViewSettings() {
		return false;
	}

}
