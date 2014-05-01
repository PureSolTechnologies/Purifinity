package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.PersonellAttributes;

public class PersonellAttributesControl extends AbstractAttributesControl {

	public PersonellAttributesControl(Composite parent, int style) {
		super(parent, style, "Personell Attributes");
		addAttributeCombo(PersonellAttributes.ANALYST_CAPABILITY);
		addAttributeCombo(PersonellAttributes.APPLICATION_EXPERIENCE);
		addAttributeCombo(PersonellAttributes.SOFTWARE_ENGINEER_CAPABILITY);
		addAttributeCombo(PersonellAttributes.VIRTUAL_MACHINE_EXPERIENCE);
		addAttributeCombo(PersonellAttributes.PROGRAMMING_EXPERIENCE);
	}
}
