package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.HardwareAttributes;

public class HardwareAttributesControl extends AbstractAttributesControl {

	public HardwareAttributesControl(Composite parent, int style) {
		super(parent, style, "Hardware Attributes");
		addAttributeCombo(HardwareAttributes.RUNTIME_PERFORMANCE_CONSTRAINTS);
		addAttributeCombo(HardwareAttributes.MEMORY_CONSTRAINTS);
		addAttributeCombo(HardwareAttributes.VOLATILITY_OF_THE_VIRTUAL_MACHINE_ENVIRONMENT);
		addAttributeCombo(HardwareAttributes.REQUIRED_TURNABOUT_TIME);
	}

}
