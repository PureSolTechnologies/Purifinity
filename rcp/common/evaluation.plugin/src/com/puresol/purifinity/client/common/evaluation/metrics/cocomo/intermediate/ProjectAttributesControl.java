package com.puresol.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.coding.metrics.cocomo.intermediate.ProjectAttributes;

public class ProjectAttributesControl extends AbstractAttributesControl {

	public ProjectAttributesControl(Composite parent, int style) {
		super(parent, style, "Project Attributes");
		addAttributeCombo(ProjectAttributes.APPLICATION_OF_SOFTWARE_ENGINEERING_METHODS);
		addAttributeCombo(ProjectAttributes.USE_OF_SOFTWARE_TOOLS);
		addAttributeCombo(ProjectAttributes.REQUIRED_DEVELOPMENT_SCHEDULE);
	}
}
