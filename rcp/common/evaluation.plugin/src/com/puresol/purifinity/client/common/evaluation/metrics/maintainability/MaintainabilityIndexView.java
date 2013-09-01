package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.puresol.purifinity.client.common.analysis.contents.CodeRangeComboViewer;
import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricDirectoryResults;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricResult;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexFileResults;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexResult;

public class MaintainabilityIndexView extends AbstractEvaluationView implements
		ISelectionChangedListener {

	private Combo codeRangeCombo;
	private CodeRangeComboViewer comboViewer;
	private final List<CodeRange> codeRanges = new ArrayList<CodeRange>();
	private HashIdFileTree path = null;
	private MaintainabilityIndexResultPanel resultPanel;

	@Override
	public void createPartControl(Composite parent) {
		initializeToolBar();
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label codeRangeLabel = new Label(composite, SWT.NONE);
		codeRangeLabel.setText("Code Range:");
		FormData fdCodeRangeLabel = new FormData();
		fdCodeRangeLabel.left = new FormAttachment(0, 10);
		fdCodeRangeLabel.right = new FormAttachment(100, -10);
		fdCodeRangeLabel.top = new FormAttachment(0, 10);
		codeRangeLabel.setLayoutData(fdCodeRangeLabel);

		codeRangeCombo = new Combo(composite, SWT.NONE);
		FormData fdCodeRangeCombo = new FormData();
		fdCodeRangeCombo.left = new FormAttachment(0, 10);
		fdCodeRangeCombo.right = new FormAttachment(100, -10);
		fdCodeRangeCombo.top = new FormAttachment(codeRangeLabel, 10);
		codeRangeCombo.setLayoutData(fdCodeRangeCombo);
		comboViewer = new CodeRangeComboViewer(codeRangeCombo);
		comboViewer.addSelectionChangedListener(this);

		resultPanel = new MaintainabilityIndexResultPanel(composite);
		FormData fdTabFolder = new FormData();
		fdTabFolder.left = new FormAttachment(0, 10);
		fdTabFolder.right = new FormAttachment(100, -10);
		fdTabFolder.top = new FormAttachment(codeRangeCombo, 10);
		resultPanel.setLayoutData(fdTabFolder);

		super.createPartControl(parent);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getToolBarManager();
		// toolbarManager.add(new RefreshAction(this));
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		showEvaluation(path, 0);
	}

	public void showEvaluation(HashIdFileTree path, int codeRangeId) {
		MaintainabilityIndexResult maintainabilityResult = null;
		if ((codeRangeId >= 0) && (codeRangeId < codeRanges.size())) {
			EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
					.createInstance(MaintainabilityIndexEvaluator.class);
			if (path.isFile()) {
				MaintainabilityIndexFileResults fileResults = (MaintainabilityIndexFileResults) evaluatorStore
						.readFileResults(path.getHashId());
				List<MaintainabilityIndexFileResult> fileResultList = fileResults
						.getResults();
				if (fileResultList != null) {
					maintainabilityResult = fileResultList.get(codeRangeId)
							.getMaintainabilityIndexResult();
				}
			}
		}
		setMaintainabilityIndexResult(maintainabilityResult);
	}

	private void setMaintainabilityIndexResult(
			MaintainabilityIndexResult maintainabilityResult) {
		resultPanel.setResult(maintainabilityResult);
	}

	private void updateCodeRanges(HashIdFileTree path) {
		codeRanges.clear();
		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(HalsteadMetricEvaluator.class);
		if (path.isFile()) {
			HalsteadMetricFileResults fileResults = (HalsteadMetricFileResults) evaluatorStore
					.readFileResults(path.getHashId());
			if (fileResults != null) {
				List<HalsteadMetricResult> fileResultList = fileResults
						.getResults();
				for (HalsteadMetricResult result : fileResultList) {
					codeRanges.add(new CodeRange(result.getCodeRangeName(),
							result.getCodeRangeName(), result
									.getCodeRangeType(), null));
				}
			}
		} else {
			HalsteadMetricDirectoryResults directoryResults = (HalsteadMetricDirectoryResults) evaluatorStore
					.readDirectoryResults(path.getHashId());
			if (directoryResults != null) {
				HalsteadMetricResult directoryResult = directoryResults
						.getResult();
				if (directoryResult != null) {
					codeRanges.add(new CodeRange(path.getName(),
							path.getName(), CodeRangeType.DIRECTORY, null));
				}
			}
		}
		comboViewer.setInput(codeRanges);
		codeRangeCombo.select(0);
	}

	@Override
	protected void updateEvaluation() {
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			path = analysisSelection.getFileTreeNode();
			updateCodeRanges(path);
			showEvaluation(path);
		} else {
			path = null;
		}
	}

	@Override
	public void setFocus() {
		// intentionally left blank
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSource() == comboViewer) {
			showEvaluation(path, codeRangeCombo.getSelectionIndex());
		}
	}
}
