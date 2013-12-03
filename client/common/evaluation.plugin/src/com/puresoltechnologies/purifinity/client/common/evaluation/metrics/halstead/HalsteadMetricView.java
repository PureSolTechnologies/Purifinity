package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.halstead;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

import com.puresoltechnologies.purifinity.client.common.analysis.contents.CodeRangeComboViewer;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadResult;

public class HalsteadMetricView extends AbstractEvaluationView implements
		ISelectionChangedListener {

	private Combo codeRangeCombo;
	private CodeRangeComboViewer comboViewer;
	private HalsteadMetricResultPanel resultPanel;
	private HalsteadOperatorsTableViewer operatorsViewer;
	private HalsteadOperandsTableViewer operandsViewer;
	private final List<CodeRange> codeRanges = new ArrayList<CodeRange>();
	private HashIdFileTree path = null;

	@Override
	public void createPartControl(Composite parent) {
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

		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		FormData fdTabFolder = new FormData();
		fdTabFolder.left = new FormAttachment(0, 10);
		fdTabFolder.right = new FormAttachment(100, -10);
		fdTabFolder.top = new FormAttachment(codeRangeCombo, 10);
		fdTabFolder.bottom = new FormAttachment(100, -10);
		tabFolder.setLayoutData(fdTabFolder);

		TabItem resultsTab = new TabItem(tabFolder, SWT.NONE);
		resultsTab.setText("Results");
		ScrolledComposite scrolled = new ScrolledComposite(tabFolder,
				SWT.V_SCROLL | SWT.H_SCROLL);
		resultsTab.setControl(scrolled);
		resultPanel = new HalsteadMetricResultPanel(scrolled);
		scrolled.setContent(resultPanel);
		scrolled.setExpandHorizontal(true);
		scrolled.setExpandVertical(true);
		scrolled.setMinSize(resultPanel.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		TabItem operatorsTab = new TabItem(tabFolder, SWT.NONE);
		operatorsTab.setText("Operators");
		Table operatorsTable = new Table(tabFolder, SWT.V_SCROLL);
		operatorsTable.setHeaderVisible(true);
		operatorsTable.setLinesVisible(true);
		operatorsTab.setControl(operatorsTable);
		operatorsViewer = new HalsteadOperatorsTableViewer(operatorsTable);

		TabItem operandsTab = new TabItem(tabFolder, SWT.NONE);
		operandsTab.setText("Operands");
		Table operandsTable = new Table(tabFolder, SWT.V_SCROLL);
		operandsTable.setHeaderVisible(true);
		operandsTable.setLinesVisible(true);
		operandsTab.setControl(operandsTable);
		operandsViewer = new HalsteadOperandsTableViewer(operandsTable);

		super.createPartControl(parent);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		showEvaluation(path, 0);
	}

	public void showEvaluation(HashIdFileTree path, int codeRangeId) {
		HalsteadResult halsteadResult = null;
		if ((codeRangeId >= 0) && (codeRangeId < codeRanges.size())) {
			EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
					.createInstance(HalsteadMetricEvaluator.class);
			if (path.isFile()) {
				HalsteadMetricFileResults fileResults = (HalsteadMetricFileResults) evaluatorStore
						.readFileResults(path.getHashId());
				List<HalsteadMetricResult> fileResultList = fileResults
						.getResults();
				if (fileResultList != null) {
					halsteadResult = fileResultList.get(codeRangeId)
							.getHalsteadResult();
				}
			} else {
				HalsteadMetricDirectoryResults directoryResults = (HalsteadMetricDirectoryResults) evaluatorStore
						.readDirectoryResults(path.getHashId());
				if (directoryResults != null) {
					HalsteadMetricResult directoryResult = directoryResults
							.getResult();
					if (directoryResult != null) {
						halsteadResult = directoryResult.getHalsteadResult();
					}
				}
			}
		}
		setHalsteadResult(halsteadResult);
	}

	private void updateCodeRanges(HashIdFileTree path) {
		codeRanges.clear();
		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(HalsteadMetricEvaluator.class);
		if (path.isFile()) {
			HalsteadMetricFileResults fileResults = (HalsteadMetricFileResults) evaluatorStore
					.readFileResults(path.getHashId());
			List<HalsteadMetricResult> fileResultList = fileResults
					.getResults();
			if (fileResultList != null) {
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

	private void setHalsteadResult(HalsteadResult halsteadResult) {
		resultPanel.setResult(halsteadResult);
		if (halsteadResult != null) {
			operatorsViewer.setInput(halsteadResult.getOperators().entrySet());
			operandsViewer.setInput(halsteadResult.getOperands().entrySet());
		} else {
			operatorsViewer.setInput(null);
			operandsViewer.setInput(null);
		}
	}

	@Override
	protected void updateEvaluation() {
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
			showEvaluation(path, codeRangeCombo.getSelectionIndex());
		}
	}

}
