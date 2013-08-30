package com.puresol.purifinity.client.common.evaluation.metrics.cocomo;

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

import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoResults;

/**
 * This panel contains the results of a CoCoMo evaluation in a nifty panel for
 * UI.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CoCoMoResultPanel extends Composite {

	private CoCoMoResults results;

	private final Group group;

	private final Label totalSourceLinesLabel;
	private final Label complexityLabel;
	private final Label developmentEffortLabel;
	private final Label scheduleEstimateLabel;
	private final Label numberOfDevelopersLabel;
	private final Label estimatedCostsLabel;

	private final Text totalSourceLinesText;
	private final Text complexityText;
	private final Text developmentEffortText;
	private final Text scheduleEstimateText;
	private final Text numberOfDevelopersText;
	private final Text estimatedCostsText;

	public CoCoMoResultPanel(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FormLayout());

		group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText("Basic Cost Construction Model Results");
		group.setLayout(new FormLayout());
		Font font = group.getFont();
		FontData fontData = font.getFontData()[0];
		Font newFont = new Font(group.getDisplay(), fontData.getName(),
				(int) (fontData.getHeight() * 1.2), fontData.getStyle()
						| SWT.BOLD);
		group.setFont(newFont);

		totalSourceLinesLabel = newLabel(null);
		complexityLabel = newLabel(totalSourceLinesLabel);
		developmentEffortLabel = newLabel(complexityLabel);
		scheduleEstimateLabel = newLabel(developmentEffortLabel);
		numberOfDevelopersLabel = newLabel(scheduleEstimateLabel);
		estimatedCostsLabel = newLabel(numberOfDevelopersLabel);

		Label totalSourceLinesEqualsLabel = addEquals(totalSourceLinesLabel);
		Label complexityEqualsLabel = addEquals(complexityLabel);
		Label developmentEffortEqualsLabel = addEquals(developmentEffortLabel);
		Label scheduleEstimateEqualsLabel = addEquals(scheduleEstimateLabel);
		Label numberOfDevelopersEqualsLabel = addEquals(numberOfDevelopersLabel);
		Label estimatedCostsEqualsLabel = addEquals(estimatedCostsLabel);

		totalSourceLinesText = newText(totalSourceLinesEqualsLabel);
		complexityText = newText(complexityEqualsLabel);
		developmentEffortText = newText(developmentEffortEqualsLabel);
		scheduleEstimateText = newText(scheduleEstimateEqualsLabel);
		numberOfDevelopersText = newText(numberOfDevelopersEqualsLabel);
		estimatedCostsText = newText(estimatedCostsEqualsLabel);

		refreshLabels();
		group.pack();
	}

	private Text newText(Label equalsLabel) {
		Text newText = new Text(group, SWT.READ_ONLY | SWT.MULTI | SWT.NO_FOCUS);
		newText.setEditable(false);
		newText.setEnabled(false);
		newText.setText("???");
		FormData fdNewText = new FormData();
		fdNewText.top = new FormAttachment(equalsLabel, 0, SWT.TOP);
		fdNewText.bottom = new FormAttachment(equalsLabel, 0, SWT.BOTTOM);
		fdNewText.left = new FormAttachment(equalsLabel, 10);
		fdNewText.right = new FormAttachment(100, -10);
		newText.setLayoutData(fdNewText);
		return newText;
	}

	private Label newLabel(Label labelAbove) {
		Label newLabel = new Label(group, SWT.NONE);
		FormData fdNewLabel = new FormData();
		if (labelAbove == null) {
			fdNewLabel.top = new FormAttachment(0, 10);
		} else {
			fdNewLabel.top = new FormAttachment(labelAbove, 10);
		}
		fdNewLabel.left = new FormAttachment(0, 10);
		newLabel.setLayoutData(fdNewLabel);
		return newLabel;
	}

	private Label addEquals(Label reference) {
		Label equals = new Label(group, SWT.NONE);
		equals.setText("=");
		FormData fdEquals = new FormData();
		fdEquals.top = new FormAttachment(reference, 0, SWT.TOP);
		fdEquals.bottom = new FormAttachment(reference, 0, SWT.BOTTOM);
		fdEquals.left = new FormAttachment(estimatedCostsLabel, 10, SWT.RIGHT);
		equals.setLayoutData(fdEquals);
		return equals;
	}

	private void refreshLabels() {
		String c1 = "??????";
		String c2 = "??????";
		String c3 = "??????";
		if (results != null) {
			c1 = String.valueOf(results.getC1());
			c2 = String.valueOf(results.getC2());
			c3 = String.valueOf(results.getC3());
		}
		totalSourceLinesLabel.setText("Total Physical Source Lines of Code");
		complexityLabel.setText("Project Complexity");
		developmentEffortLabel.setText("Estimated Development Effort\n"
				+ "(Person-Months = " + c1 + " * kSLOC^" + c2 + ")");
		scheduleEstimateLabel.setText("Estimated Schedule\n"
				+ "(Months = 2.5 * Person-Months^" + c3 + ")");
		numberOfDevelopersLabel
				.setText("Estimated Average Number of Developers\n"
						+ "(Effort / Schedule)");
		estimatedCostsLabel.setText("Estimated Total Cost of Development\n"
				+ "(2.4 * Average Salary * Number of Developers)");
	}

	private void refreshTexts() {
		if (results != null) {
			totalSourceLinesText.setText(String.valueOf(results.getKsloc())
					+ " kSLOC");
			complexityText.setText(results.getComplexity().name());
			developmentEffortText.setText(String.valueOf(results
					.getPersonMonth())
					+ " Person-Months\n("
					+ String.valueOf(results.getPersonYears())
					+ " Person-Years)");
			scheduleEstimateText.setText(String.valueOf(results
					.getScheduledMonth())
					+ " Months\n("
					+ String.valueOf(results.getScheduledYears()) + " Years)");
			numberOfDevelopersText
					.setText(String.valueOf(results.getTeamSize()));
			estimatedCostsText.setText(String.valueOf(results
					.getEstimatedCosts()) + " k" + results.getCurrency());
		} else {
			totalSourceLinesText.setText("");
			complexityText.setText("");
			developmentEffortText.setText("");
			scheduleEstimateText.setText("");
			numberOfDevelopersText.setText("");
			estimatedCostsText.setText("");
		}
		group.layout();
		group.redraw();
		layout();
		redraw();
	}

	public void setResults(CoCoMoResults results) {
		this.results = results;
		refreshLabels();
		refreshTexts();
	}
}
