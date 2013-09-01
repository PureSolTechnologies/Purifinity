package com.puresol.purifinity.client.common.evaluation.metrics.halstead;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.purifinity.coding.metrics.halstead.HalsteadResult;

public class HalsteadMetricResultPanel extends Composite {

	private final Group group;

	private final Label differentOperatorsLabel;
	private final Label differentOperandsLabel;
	private final Label totalOperatorsLabel;
	private final Label totalOperandsLabel;
	private final Label vocabularySizeLabel;
	private final Label programLengthLabel;
	private final Label halsteadLengthLabel;
	private final Label halsteadVolumeLabel;
	private final Label difficultyLabel;
	private final Label programLevelLabel;
	private final Label implementationEffortLabel;
	private final Label implementationTimeLabel;
	private final Label estimatedBugsLabel;

	private final Text differentOperators;
	private final Text differentOperands;
	private final Text totalOperators;
	private final Text totalOperands;
	private final Text vocabularySize;
	private final Text programLength;
	private final Text halsteadLength;
	private final Text halsteadVolume;
	private final Text difficulty;
	private final Text programLevel;
	private final Text implementationEffort;
	private final Text implementationTime;
	private final Text estimatedBugs;

	public HalsteadMetricResultPanel(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());

		group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText("Halstead Metric Results");
		FormLayout layout = new FormLayout();
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.marginTop = 10;
		layout.marginBottom = 10;
		group.setLayout(layout);
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
	}

	private Label createLabel(Label referenceLabel) {
		Label newLabel = new Label(group, SWT.NONE);
		FormData fdNewLabel = new FormData();
		fdNewLabel.left = new FormAttachment(0, 0);
		if (referenceLabel != null) {
			fdNewLabel.top = new FormAttachment(referenceLabel, 10);
		} else {
			fdNewLabel.top = new FormAttachment(0, 0);
		}
		newLabel.setLayoutData(fdNewLabel);
		return newLabel;
	}

	private Text createText(Label referenceLabel) {
		Text newText = new Text(group, SWT.READ_ONLY | SWT.NO_FOCUS);
		newText.setEditable(false);
		FormData fdNewText = new FormData();
		fdNewText.left = new FormAttachment(differentOperandsLabel, 10);
		fdNewText.right = new FormAttachment(100, 0);
		fdNewText.top = new FormAttachment(referenceLabel, 0, SWT.TOP);
		fdNewText.bottom = new FormAttachment(referenceLabel, 0, SWT.BOTTOM);
		newText.setLayoutData(fdNewText);
		return newText;
	}

	public void setResult(HalsteadResult halsteadResult) {
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
		group.layout();
		group.redraw();
	}

}
