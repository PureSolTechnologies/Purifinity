package com.puresol.purifinity.client.common.evaluation.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.commons.utils.StringUtils;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;

/**
 * This components takes a {@link QualityCharacteristic} and shows all its
 * information for the user.
 * 
 * @author Rick-Rainer Ludwig
 */
public class QualityCharacteristicInformationComponent extends Composite {

	private final Label name;
	private final Text description;
	private final Text mainCharacteristic;

	private QualityCharacteristic qualityCharacteristic;

	public QualityCharacteristicInformationComponent(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		name = new Label(this, SWT.BORDER);
		FormData fdName = new FormData();
		fdName.top = new FormAttachment(0, 10);
		fdName.left = new FormAttachment(0, 10);
		fdName.bottom = new FormAttachment(0, 32);
		fdName.right = new FormAttachment(100, -10);
		name.setLayoutData(fdName);

		Label mainCharacteristicLabel = new Label(this, SWT.NONE);
		FormData fdMainCharacteristicLabel = new FormData();
		fdMainCharacteristicLabel.top = new FormAttachment(name, 10);
		fdMainCharacteristicLabel.left = new FormAttachment(0, 10);
		mainCharacteristicLabel.setLayoutData(fdMainCharacteristicLabel);
		mainCharacteristicLabel.setText("Unit:");

		mainCharacteristic = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		FormData fdMainCharacteristic = new FormData();
		fdMainCharacteristic.top = new FormAttachment(name, 10);
		fdMainCharacteristic.left = new FormAttachment(mainCharacteristicLabel,
				10);
		fdMainCharacteristic.right = new FormAttachment(100, -10);
		mainCharacteristic.setLayoutData(fdMainCharacteristic);

		Label descriptionLabel = new Label(this, SWT.NONE);
		FormData fdDescriptionLabel = new FormData();
		fdDescriptionLabel.top = new FormAttachment(mainCharacteristicLabel, 10);
		fdDescriptionLabel.left = new FormAttachment(0, 10);
		descriptionLabel.setLayoutData(fdDescriptionLabel);
		descriptionLabel.setText("Description:");

		description = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		FormData fdDescription = new FormData();
		fdDescription.left = new FormAttachment(0, 10);
		fdDescription.bottom = new FormAttachment(100, -10);
		fdDescription.right = new FormAttachment(100, -10);
		fdDescription.top = new FormAttachment(descriptionLabel, 10);
		description.setLayoutData(fdDescription);
	}

	public void setParameter(QualityCharacteristic qualityCharacteristic) {
		this.qualityCharacteristic = qualityCharacteristic;
		refresh();
	}

	private void refresh() {
		name.setText(qualityCharacteristic.getName());
		mainCharacteristic.setText(qualityCharacteristic
				.getMainCharacteristic().getName());
		description.setText(StringUtils.wrapLinesByWords(
				qualityCharacteristic.getDescription(), 32));
	}

}
