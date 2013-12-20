package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.swt.widgets.Composite;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.ProductAttributes;

public class ProductAttributesContol extends AbstractAttributesControl {

	public ProductAttributesContol(Composite parent, int style) {
		super(parent, style, "Product Attributes");
		addAttributeCombo(ProductAttributes.REQUIRED_SOFTWARE_RELIABILITY);
		addAttributeCombo(ProductAttributes.SIZE_OF_APPLICATION_DATABASE);
		addAttributeCombo(ProductAttributes.COMPLEXITY_OF_THE_PRODUCT);
	}
}
