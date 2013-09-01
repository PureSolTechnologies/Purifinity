package com.puresol.purifinity.client.common.evaluation.metrics.halstead;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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

	private Group group;

	private Label differentOperatorsLabel;
	private Label differentOperandsLabel;
	private Label totalOperatorsLabel;
	private Label totalOperandsLabel;
	private Label vocabularySizeLabel;
	private Label programLengthLabel;
	private Label halsteadLengthLabel;
	private Label halsteadVolumeLabel;
	private Label difficultyLabel;
	private Label programLevelLabel;
	private Label implementationEffortLabel;
	private Label implementationTimeLabel;
	private Label estimatedBugsLabel;

	private Text differentOperators;
	private Text differentOperands;
	private Text totalOperators;
	private Text totalOperands;
	private Text vocabularySize;
	private Text programLength;
	private Text halsteadLength;
	private Text halsteadVolume;
	private Text difficulty;
	private Text programLevel;
	private Text implementationEffort;
	private Text implementationTime;
	private Text estimatedBugs;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new RowLayout());

		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setText("Halstead Metric Results");
		group.setLayout(new FormLayout());
		Font font = group.getFont();
		FontData fontData = font.getFontData()[0];
		Font newFont = new Font(group.getDisplay(), fontData.getName(),
				(int) (fontData.getHeight() * 1.2), fontData.getStyle()
						| SWT.BOLD);
		group.setFont(newFont);

		differentOperatorsLabel = createLabel(null);
		differentOperandsLabel = createLabel(differentOperatorsLabel);
		totalOperatorsLabel = createLabel(differentOperandsLabel);
		totalOperandsLabel = createLabel(totalOperatorsLabel);
		vocabularySizeLabel = createLabel(totalOperandsLabel);
		programLengthLabel = createLabel(vocabularySizeLabel);
		halsteadLengthLabel = createLabel(programLengthLabel);
		halsteadVolumeLabel = createLabel(halsteadLengthLabel);
		difficultyLabel = createLabel(halsteadVolumeLabel);
		programLevelLabel = createLabel(difficultyLabel);
		implementationEffortLabel = createLabel(programLevelLabel);
		implementationTimeLabel = createLabel(implementationEffortLabel);
		estimatedBugsLabel = createLabel(implementationTimeLabel);

		differentOperandsLabel.setText("Number of different Operands");
		differentOperatorsLabel.setText("Number of different Operators");
		totalOperandsLabel.setText("Total Number of Operands");
		totalOperatorsLabel.setText("Total Number of Operators");
		vocabularySizeLabel.setText("Vocabulary Size");
		programLengthLabel.setText("Program Length");
		halsteadLengthLabel.setText("Halstead Length");
		halsteadVolumeLabel.setText("Halstead Volume");
		difficultyLabel.setText("Difficulty");
		programLevelLabel.setText("Program Level");
		implementationEffortLabel.setText("Implementation Effort");
		implementationTimeLabel.setText("Implementation Time");
		estimatedBugsLabel.setText("Estimated Bugs");

		differentOperators = createText(differentOperatorsLabel);
		differentOperands = createText(differentOperandsLabel);
		totalOperators = createText(totalOperatorsLabel);
		totalOperands = createText(totalOperandsLabel);
		vocabularySize = createText(vocabularySizeLabel);
		programLength = createText(programLengthLabel);
		halsteadLength = createText(halsteadLengthLabel);
		halsteadVolume = createText(halsteadVolumeLabel);
		difficulty = createText(difficultyLabel);
		programLevel = createText(programLevelLabel);
		implementationEffort = createText(implementationEffortLabel);
		implementationTime = createText(implementationTimeLabel);
		estimatedBugs = createText(estimatedBugsLabel);

		group.pack();

		super.createPartControl(parent);
	}

	private Label createLabel(Label referenceLabel) {
		Label newLabel = new Label(group, SWT.NONE);
		FormData fdNewLabel = new FormData();
		fdNewLabel.left = new FormAttachment(0, 10);
		if (referenceLabel != null) {
			fdNewLabel.top = new FormAttachment(referenceLabel, 10);
		} else {
			fdNewLabel.top = new FormAttachment(0, 10);
		}
		newLabel.setLayoutData(fdNewLabel);
		return newLabel;
	}

	private Text createText(Label referenceLabel) {
		Text newText = new Text(group, SWT.READ_ONLY | SWT.NO_FOCUS);
		newText.setEditable(false);
		FormData fdNewText = new FormData();
		fdNewText.left = new FormAttachment(differentOperandsLabel, 10);
		fdNewText.right = new FormAttachment(100, -10);
		fdNewText.top = new FormAttachment(referenceLabel, 0, SWT.TOP);
		fdNewText.bottom = new FormAttachment(referenceLabel, 0, SWT.BOTTOM);
		newText.setLayoutData(fdNewText);
		return newText;
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
		setResult(halsteadResult);
	}

	private void setResult(HalsteadResult halsteadResult) {
		if (halsteadResult != null) {
			differentOperators.setText(String.valueOf(halsteadResult
					.getDifferentOperators()));
			differentOperands.setText(String.valueOf(halsteadResult
					.getDifferentOperands()));
			totalOperators.setText(String.valueOf(halsteadResult
					.getTotalOperators()));
			totalOperands.setText(String.valueOf(halsteadResult
					.getTotalOperands()));
			vocabularySize.setText(String.valueOf(halsteadResult
					.getVocabularySize()));
			programLength.setText(String.valueOf(halsteadResult
					.getProgramLength()));
			halsteadLength.setText(String.valueOf(halsteadResult
					.getHalsteadLength()));
			halsteadVolume.setText(String.valueOf(halsteadResult
					.getHalsteadVolume()));
			difficulty.setText(String.valueOf(halsteadResult.getDifficulty()));
			programLevel.setText(String.valueOf(halsteadResult
					.getProgramLevel()));
			implementationEffort.setText(String.valueOf(halsteadResult
					.getImplementationEffort()));
			implementationTime.setText(String.valueOf(halsteadResult
					.getImplementationTime()));
			estimatedBugs.setText(String.valueOf(halsteadResult
					.getEstimatedBugs()));
		} else {
			differentOperators.setText("");
			differentOperands.setText("");
			totalOperators.setText("");
			totalOperands.setText("");
			vocabularySize.setText("");
			programLength.setText("");
			halsteadLength.setText("");
			halsteadVolume.setText("");
			difficulty.setText("");
			programLevel.setText("");
			implementationEffort.setText("");
			implementationTime.setText("");
			estimatedBugs.setText("");
		}
	}

	@Override
	protected void updateEvaluation() {
		FileAnalysisSelection analysisSelection = getAnalysisSelection();
		if (analysisSelection != null) {
			showEvaluation(analysisSelection.getFileTreeNode());
			group.layout();
			group.redraw();
		}
	}

	@Override
	public void setFocus() {
		// intentionally left blank
	}

}
