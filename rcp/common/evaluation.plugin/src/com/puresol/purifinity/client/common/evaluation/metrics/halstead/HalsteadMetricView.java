package com.puresol.purifinity.client.common.evaluation.metrics.halstead;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricDirectoryResults;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricResult;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadResult;

public class HalsteadMetricView extends AbstractEvaluationView {

	private Combo codeRangeCombo;
	private HalsteadMetricResultPanel resultPanel;
	private HalsteadOperatorsTableViewer operatorsViewer;
	private HalsteadOperandsTableViewer operandsViewer;

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

		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		FormData fdTabFolder = new FormData();
		fdTabFolder.left = new FormAttachment(0, 10);
		fdTabFolder.right = new FormAttachment(100, -10);
		fdTabFolder.top = new FormAttachment(codeRangeCombo, 10);
		tabFolder.setLayoutData(fdTabFolder);

		TabItem resultsTab = new TabItem(tabFolder, SWT.NONE);
		resultsTab.setText("Results");
		resultPanel = new HalsteadMetricResultPanel(tabFolder);
		resultsTab.setControl(resultPanel);

		TabItem operatorsTab = new TabItem(tabFolder, SWT.NONE);
		operatorsTab.setText("Operators");
		Table operatorsTable = new Table(tabFolder, SWT.NONE);
		operatorsTable.setHeaderVisible(true);
		operatorsTable.setLinesVisible(true);
		operatorsTab.setControl(operatorsTable);
		operatorsViewer = new HalsteadOperatorsTableViewer(operatorsTable);

		TabItem operandsTab = new TabItem(tabFolder, SWT.NONE);
		operandsTab.setText("Operands");
		Table operandsTable = new Table(tabFolder, SWT.NONE);
		operandsTable.setHeaderVisible(true);
		operandsTable.setLinesVisible(true);
		operandsTab.setControl(operandsTable);
		operandsViewer = new HalsteadOperandsTableViewer(operandsTable);

		super.createPartControl(parent);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(HalsteadMetricEvaluator.class);
		HalsteadResult halsteadResult = null;
		if (path.isFile()) {
			HalsteadMetricFileResults fileResults = (HalsteadMetricFileResults) evaluatorStore
					.readFileResults(path.getHashId());
			List<HalsteadMetricResult> fileResult = fileResults.getResults();
			if (fileResult != null) {
				halsteadResult = fileResult.get(0).getHalsteadResult();
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
		// set results...
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
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			showEvaluation(analysisSelection.getFileTreeNode());
		}
	}

	@Override
	public void setFocus() {
		// intentionally left blank
	}

}
