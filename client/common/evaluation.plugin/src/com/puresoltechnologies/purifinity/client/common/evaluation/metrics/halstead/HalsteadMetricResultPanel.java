package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.halstead;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadResult;

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

	private final Label differentOperatorsSignLabel;
	private final Label differentOperandsSignLabel;
	private final Label totalOperatorsSignLabel;
	private final Label totalOperandsSignLabel;
	private final Label vocabularySizeSignLabel;
	private final Label programLengthSignLabel;
	private final Label halsteadLengthSignLabel;
	private final Label halsteadVolumeSignLabel;
	private final Label difficultySignLabel;
	private final Label programLevelSignLabel;
	private final Label implementationEffortSignLabel;
	private final Label implementationTimeSignLabel;
	private final Label estimatedBugsSignLabel;

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

		totalOperatorsLabel = createLabel(null, "Total Number of Operators");
		totalOperandsLabel = createLabel(totalOperatorsLabel,
				"Total Number of Operands");
		differentOperatorsLabel = createLabel(totalOperandsLabel,
				"Number of different Operators");
		differentOperandsLabel = createLabel(differentOperatorsLabel,
				"Number of different Operands");
		vocabularySizeLabel = createLabel(differentOperandsLabel,
				"Vocabulary Size");
		programLengthLabel = createLabel(vocabularySizeLabel, "Program Length");
		halsteadVolumeLabel = createLabel(programLengthLabel, "Halstead Volume");
		halsteadLengthLabel = createLabel(halsteadVolumeLabel,
				"Halstead Length");
		difficultyLabel = createLabel(halsteadLengthLabel, "Difficulty");
		programLevelLabel = createLabel(difficultyLabel, "Program Level");
		implementationEffortLabel = createLabel(programLevelLabel,
				"Implementation Effort");
		implementationTimeLabel = createLabel(implementationEffortLabel,
				"Implementation Time");
		estimatedBugsLabel = createLabel(implementationTimeLabel,
				"Number of delivered bugs");

		totalOperatorsSignLabel = createSignLabel(totalOperatorsLabel, "N1");
		totalOperandsSignLabel = createSignLabel(totalOperandsLabel, "N2");
		differentOperatorsSignLabel = createSignLabel(differentOperatorsLabel,
				"n1");
		differentOperandsSignLabel = createSignLabel(differentOperandsLabel,
				"n2");
		programLengthSignLabel = createSignLabel(programLengthLabel, "N");
		vocabularySizeSignLabel = createSignLabel(vocabularySizeLabel, "n");
		halsteadVolumeSignLabel = createSignLabel(halsteadVolumeLabel, "V");
		halsteadLengthSignLabel = createSignLabel(halsteadLengthLabel, "Hl");
		difficultySignLabel = createSignLabel(difficultyLabel, "D");
		programLevelSignLabel = createSignLabel(programLevelLabel, "L");
		implementationEffortSignLabel = createSignLabel(
				implementationEffortLabel, "E");
		implementationTimeSignLabel = createSignLabel(implementationTimeLabel,
				"T");
		estimatedBugsSignLabel = createSignLabel(estimatedBugsLabel, "B");

		differentOperators = createText(differentOperatorsSignLabel);
		differentOperands = createText(differentOperandsSignLabel);
		totalOperators = createText(totalOperatorsSignLabel);
		totalOperands = createText(totalOperandsSignLabel);
		vocabularySize = createText(vocabularySizeSignLabel);
		programLength = createText(programLengthSignLabel);
		halsteadLength = createText(halsteadLengthSignLabel);
		halsteadVolume = createText(halsteadVolumeSignLabel);
		difficulty = createText(difficultySignLabel);
		programLevel = createText(programLevelSignLabel);
		implementationEffort = createText(implementationEffortSignLabel);
		implementationTime = createText(implementationTimeSignLabel);
		estimatedBugs = createText(estimatedBugsSignLabel);

		group.pack();
	}

	private Label createLabel(Label referenceLabel, String text) {
		Label newLabel = new Label(group, SWT.NONE);
		newLabel.setText(text);
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

	private Label createSignLabel(Label referenceLabel, String sign) {
		Label newLabel = new Label(group, SWT.NONE);
		newLabel.setText(sign + "=");
		FormData fdNewLabel = new FormData();
		fdNewLabel.left = new FormAttachment(differentOperatorsLabel, 10);
		fdNewLabel.top = new FormAttachment(referenceLabel, 0, SWT.TOP);
		fdNewLabel.bottom = new FormAttachment(referenceLabel, 0, SWT.BOTTOM);
		newLabel.setLayoutData(fdNewLabel);
		return newLabel;
	}

	private Text createText(Label referenceLabel) {
		Text newText = new Text(group, SWT.READ_ONLY | SWT.NO_FOCUS);
		newText.setEditable(false);
		FormData fdNewText = new FormData();
		fdNewText.left = new FormAttachment(referenceLabel, 10);
		fdNewText.right = new FormAttachment(100, 0);
		fdNewText.top = new FormAttachment(referenceLabel, 0, SWT.TOP);
		fdNewText.bottom = new FormAttachment(referenceLabel, 0, SWT.BOTTOM);
		newText.setLayoutData(fdNewText);
		return newText;
	}

	public void setResult(HalsteadResult halsteadResult) {
		if (halsteadResult != null) {
			totalOperators.setText(String.valueOf(halsteadResult
					.getTotalOperators()));
			totalOperands.setText(String.valueOf(halsteadResult
					.getTotalOperands()));
			differentOperators.setText(String.valueOf(halsteadResult
					.getDifferentOperators()));
			differentOperands.setText(String.valueOf(halsteadResult
					.getDifferentOperands()));
			vocabularySize.setText(String.valueOf(halsteadResult
					.getVocabularySize()));
			programLength.setText(String.valueOf(halsteadResult
					.getProgramLength()));
			halsteadVolume.setText(String.valueOf(round(halsteadResult
					.getHalsteadVolume())));
			halsteadLength.setText(String.valueOf(round(halsteadResult
					.getHalsteadLength())));
			difficulty.setText(String.valueOf(round(halsteadResult
					.getDifficulty())));
			programLevel.setText(String.valueOf(round(halsteadResult
					.getProgramLevel())));
			implementationEffort.setText(convertToExponent(halsteadResult
					.getImplementationEffort()));
			implementationTime.setText(convertTime(halsteadResult));
			estimatedBugs.setText(String.valueOf(round(halsteadResult
					.getEstimatedBugs())));
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

	private String convertToExponent(double value) {
		int exp = (int) Math.floor(Math.log10(value));
		double mantissa = value / Math.exp(exp * Math.log(10.0));
		if (exp != 0) {
			return String.valueOf(round(mantissa)) + "*10^"
					+ String.valueOf(exp);
		} else {
			return String.valueOf(round(mantissa));
		}
	}

	private String convertTime(HalsteadResult halsteadResult) {
		double seconds = round(halsteadResult.getImplementationTime());
		if (seconds < 60.0) {
			return String.valueOf(seconds) + "s";
		}
		double minutes = round(halsteadResult.getImplementationTime() / 60.0);
		if (minutes < 60.0) {
			return String.valueOf(minutes) + "min";
		}
		double hours = round(halsteadResult.getImplementationTime() / 3600.0);
		if (hours < 24.0) {
			return String.valueOf(hours) + "h";
		}
		double days = round(halsteadResult.getImplementationTime() / 3600.0 / 24.0);
		if (days < 365.25) {
			return String.valueOf(days) + "d";
		}
		return "~"
				+ round(halsteadResult.getImplementationTime() / 3600.0 / 24.0 / 365.25)
				+ "y";
	}

	private double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}
}
