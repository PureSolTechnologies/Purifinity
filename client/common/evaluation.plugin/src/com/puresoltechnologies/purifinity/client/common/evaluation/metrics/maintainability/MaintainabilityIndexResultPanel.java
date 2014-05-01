package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

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

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexResult;

public class MaintainabilityIndexResultPanel extends Composite {

	private final Group group;

	private final Label miWocLabel;
	private final Label miCwLabel;
	private final Label miLabel;

	private final Text miWoc;
	private final Text miCw;
	private final Text mi;

	public MaintainabilityIndexResultPanel(Composite parent) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());

		group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText("Maintainability Index Results");
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

		miWocLabel = createLabel(null);
		miCwLabel = createLabel(miWocLabel);
		miLabel = createLabel(miCwLabel);

		miCwLabel.setText("MIcw");
		miWocLabel.setText("MIwoc");
		miLabel.setText("MI");

		miWoc = createText(miWocLabel);
		miCw = createText(miCwLabel);
		mi = createText(miLabel);

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
		fdNewText.left = new FormAttachment(miCwLabel, 10);
		fdNewText.right = new FormAttachment(100, 0);
		fdNewText.top = new FormAttachment(referenceLabel, 0, SWT.TOP);
		fdNewText.bottom = new FormAttachment(referenceLabel, 0, SWT.BOTTOM);
		newText.setLayoutData(fdNewText);
		return newText;
	}

	public void setResult(MaintainabilityIndexResult maintainabilityResult) {
		if (maintainabilityResult != null) {
			miWoc.setText(String.valueOf(maintainabilityResult.getMIwoc()));
			miCw.setText(String.valueOf(maintainabilityResult.getMIcw()));
			mi.setText(String.valueOf(maintainabilityResult.getMI()));
		} else {
			miWoc.setText("");
			miCw.setText("");
			mi.setText("");
		}
		group.layout();
		group.redraw();
	}

}
