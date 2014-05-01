package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import static com.puresoltechnologies.purifinity.client.common.ui.SWTUtils.DEFAULT_MARGIN;

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

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.IntermediateCoCoMoResults;

/**
 * This panel contains the results of a CoCoMo evaluation in a nifty panel for
 * UI.
 * 
 * @author Rick-Rainer Ludwig
 */
public class IntermediateCoCoMoResultPanel extends Composite {

	private IntermediateCoCoMoResults results;

	private final Group group;

	private final Label totalSourceLinesLabel;
	private final Label softwareProjectLabel;
	private final Label developmentEffortLabel;
	private final Label scheduleEstimateLabel;
	private final Label numberOfDevelopersLabel;
	private final Label estimatedCostsLabel;

	private final Text totalSourceLinesText;
	private final Text projectText;
	private final Text developmentEffortText;
	private final Text scheduleEstimateText;
	private final Text numberOfDevelopersText;
	private final Text estimatedCostsText;

	public IntermediateCoCoMoResultPanel(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FormLayout());

		group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText("Intermediate Cost Construction Model Results");
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

		totalSourceLinesLabel = newLabel(null, false);
		softwareProjectLabel = newLabel(totalSourceLinesLabel, false);
		developmentEffortLabel = newLabel(softwareProjectLabel, false);
		scheduleEstimateLabel = newLabel(developmentEffortLabel, false);
		numberOfDevelopersLabel = newLabel(scheduleEstimateLabel, false);
		estimatedCostsLabel = newLabel(numberOfDevelopersLabel, true);

		Label totalSourceLinesEqualsLabel = addEquals(totalSourceLinesLabel);
		Label softwareProjectsEqualsLabel = addEquals(softwareProjectLabel);
		Label developmentEffortEqualsLabel = addEquals(developmentEffortLabel);
		Label scheduleEstimateEqualsLabel = addEquals(scheduleEstimateLabel);
		Label numberOfDevelopersEqualsLabel = addEquals(numberOfDevelopersLabel);
		Label estimatedCostsEqualsLabel = addEquals(estimatedCostsLabel);

		totalSourceLinesText = newText(totalSourceLinesEqualsLabel);
		projectText = newText(softwareProjectsEqualsLabel);
		developmentEffortText = newText(developmentEffortEqualsLabel);
		scheduleEstimateText = newText(scheduleEstimateEqualsLabel);
		numberOfDevelopersText = newText(numberOfDevelopersEqualsLabel);
		estimatedCostsText = newText(estimatedCostsEqualsLabel);

		refreshLabels();
		group.pack();
	}

	private Label newLabel(Label labelAbove, boolean last) {
		Label newLabel = new Label(group, SWT.NONE);
		FormData fdNewLabel = new FormData();
		if (labelAbove == null) {
			fdNewLabel.top = new FormAttachment(0, 0);
		} else {
			fdNewLabel.top = new FormAttachment(labelAbove, DEFAULT_MARGIN);
		}
		fdNewLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
		if (last) {
			fdNewLabel.bottom = new FormAttachment(100, -DEFAULT_MARGIN);
		}
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

	private Text newText(Label equalsLabel) {
		Text newText = new Text(group, SWT.READ_ONLY | SWT.MULTI | SWT.NO_FOCUS);
		newText.setEditable(false);
		FormData fdNewText = new FormData();
		fdNewText.top = new FormAttachment(equalsLabel, 0, SWT.TOP);
		fdNewText.bottom = new FormAttachment(equalsLabel, 0, SWT.BOTTOM);
		fdNewText.left = new FormAttachment(equalsLabel, 10);
		fdNewText.right = new FormAttachment(100, 0);
		newText.setLayoutData(fdNewText);
		return newText;
	}

	private void refreshTexts() {
		if (results != null) {
			totalSourceLinesText.setText(String.valueOf(results.getKsloc())
					+ " kSLOC");
			projectText.setText(results.getProject().name());
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
			projectText.setText("");
			developmentEffortText.setText("");
			scheduleEstimateText.setText("");
			numberOfDevelopersText.setText("");
			estimatedCostsText.setText("");
		}
	}

	private void refreshLabels() {
		String ai = "??????";
		String bi = "??????";
		String ci = "??????";
		String di = "??????";
		String eaf = "??????";
		if (results != null) {
			ai = String.valueOf(results.getProject().getAi());
			bi = String.valueOf(results.getProject().getBi());
			ci = String.valueOf(results.getProject().getCi());
			di = String.valueOf(results.getProject().getDi());
			eaf = String.valueOf(results.getEAF());
		}
		totalSourceLinesLabel.setText("Total Physical Source Lines of Code");
		softwareProjectLabel.setText("Software Project");
		developmentEffortLabel.setText("Estimated Development Effort\n"
				+ "(Person-Months = " + ai + " * kSLOC^" + bi + " * " + eaf
				+ ")");
		scheduleEstimateLabel.setText("Estimated Schedule\n" + "(Months = "
				+ ci + " * Person-Months^" + di + ")");
		numberOfDevelopersLabel
				.setText("Estimated Average Number of Developers\n"
						+ "(Effort / Schedule)");
		estimatedCostsLabel.setText("Estimated Total Cost of Development\n"
				+ "(2.4 * Average Salary * Number of Developers)");
	}

	public void setResults(IntermediateCoCoMoResults results) {
		this.results = results;
		refreshLabels();
		refreshTexts();
		group.layout();
		group.redraw();
		layout();
		redraw();
	}
}
