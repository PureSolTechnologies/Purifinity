package com.puresol.purifinity.client.common.ui.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.commons.utils.StringUtils;
import com.puresol.commons.utils.math.Parameter;

/**
 * This components takes a {@link Parameter} and shows all its information for
 * the user.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ParameterInformationComponent extends Composite {

	private final Label name;
	private final Text description;
	private final Text unit;
	private final Text type;
	private final Text levelOfMeasurement;

	private Parameter<?> parameter;

	public ParameterInformationComponent(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		name = new Label(this, SWT.BORDER);
		FormData fdName = new FormData();
		fdName.top = new FormAttachment(0, 10);
		fdName.left = new FormAttachment(0, 10);
		fdName.bottom = new FormAttachment(0, 32);
		fdName.right = new FormAttachment(100, -10);
		name.setLayoutData(fdName);

		Label unitLabel = new Label(this, SWT.NONE);
		FormData fdUnitLabel = new FormData();
		fdUnitLabel.top = new FormAttachment(name, 10);
		fdUnitLabel.left = new FormAttachment(0, 10);
		unitLabel.setLayoutData(fdUnitLabel);
		unitLabel.setText("Unit:");

		unit = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		FormData fdUnit = new FormData();
		fdUnit.top = new FormAttachment(name, 10);
		fdUnit.left = new FormAttachment(unitLabel, 10);
		fdUnit.right = new FormAttachment(100, -10);
		unit.setLayoutData(fdUnit);

		Label typeLabel = new Label(this, SWT.NONE);
		FormData fdTypeLabel = new FormData();
		fdTypeLabel.top = new FormAttachment(unitLabel, 10);
		fdTypeLabel.left = new FormAttachment(0, 10);
		typeLabel.setLayoutData(fdTypeLabel);
		typeLabel.setText("Type:");

		type = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		FormData fdType = new FormData();
		fdType.top = new FormAttachment(unit);
		fdType.left = new FormAttachment(typeLabel, 10);
		fdType.right = new FormAttachment(100, -10);
		type.setLayoutData(fdType);

		Label levelOfMeasurementLabel = new Label(this, SWT.NONE);
		fdUnitLabel.right = new FormAttachment(levelOfMeasurementLabel, 0,
				SWT.RIGHT);
		fdTypeLabel.right = new FormAttachment(levelOfMeasurementLabel, 0,
				SWT.RIGHT);
		FormData fdLevelOfMeasurementLabel = new FormData();
		fdLevelOfMeasurementLabel.top = new FormAttachment(typeLabel, 10);
		fdLevelOfMeasurementLabel.left = new FormAttachment(0, 10);
		levelOfMeasurementLabel.setLayoutData(fdLevelOfMeasurementLabel);
		levelOfMeasurementLabel.setText("Level of Measurement:");

		levelOfMeasurement = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		FormData fdLevelOfMeasurement = new FormData();
		fdLevelOfMeasurement.top = new FormAttachment(type);
		fdLevelOfMeasurement.left = new FormAttachment(levelOfMeasurementLabel,
				10);
		fdLevelOfMeasurement.right = new FormAttachment(100, -10);
		levelOfMeasurement.setLayoutData(fdLevelOfMeasurement);

		Label descriptionLabel = new Label(this, SWT.NONE);
		FormData fdDescriptionLabel = new FormData();
		fdDescriptionLabel.top = new FormAttachment(levelOfMeasurementLabel, 10);
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

	public Parameter<?> getParameter() {
		return parameter;
	}

	public void setParameter(Parameter<?> parameter) {
		this.parameter = parameter;
		refresh();
	}

	private void refresh() {
		name.setText(parameter.getName());
		description.setText(StringUtils.wrapLinesByWords(
				parameter.getDescription(), 32));
		unit.setText(parameter.getUnit());
		type.setText(parameter.getType().getSimpleName());
		levelOfMeasurement.setText(parameter.getLevelOfMeasurement().name());
	}
}
