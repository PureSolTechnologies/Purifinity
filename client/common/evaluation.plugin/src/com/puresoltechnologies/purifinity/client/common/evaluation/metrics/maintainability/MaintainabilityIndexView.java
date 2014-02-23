package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Status;
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

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.CodeRangeComboViewer;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

public class MaintainabilityIndexView extends AbstractEvaluationView implements
		ISelectionChangedListener {

	private Combo codeRangeCombo;
	private CodeRangeComboViewer comboViewer;
	private final List<CodeRange> codeRanges = new ArrayList<CodeRange>();
	private AnalysisFileTree path = null;
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
	public void showEvaluation(AnalysisFileTree path)
			throws EvaluationStoreException {
		showEvaluation(path, 0);
	}

	public void showEvaluation(AnalysisFileTree path, int codeRangeId)
			throws EvaluationStoreException {
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

	private void updateCodeRanges(AnalysisFileTree path)
			throws EvaluationStoreException {
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
	protected void clear() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateView() throws EvaluationStoreException {
		AnalysisSelection analysisSelection = getAnalysisSelection();
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
			try {
				showEvaluation(path, codeRangeCombo.getSelectionIndex());
			} catch (EvaluationStoreException e) {
				Activator activator = Activator.getDefault();
				activator.getLog().log(
						new Status(Status.ERROR, activator.getBundle()
								.getSymbolicName(),
								"Could not handle new selection.", e));
			}
		}
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
