package com.puresol.coding.client.controls;

import org.eclipse.core.runtime.ILog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import swing2swt.layout.BoxLayout;

import com.puresol.coding.client.Activator;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MetricsControl extends Composite {

    private static final ILog logger = Activator.getDefault().getLog();

    public MetricsControl(Composite parent, int style) {
	super(parent, style);
	setLayout(new BoxLayout(BoxLayout.Y_AXIS));

	Composite composite = new Composite(this, SWT.NONE);
	composite.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

	Composite composite_1 = new Composite(composite, SWT.NONE);
	composite_1.setLayout(new BoxLayout(BoxLayout.X_AXIS));

	Combo combo = new Combo(composite_1, SWT.NONE);

	Label lblNewLabel = new Label(composite_1, SWT.NONE);
	lblNewLabel.setText("Explanation");

	Composite composite_2 = new Composite(composite, SWT.NONE);
	composite_2.setLayout(new FillLayout(SWT.VERTICAL));

	Label lblMetric = new Label(composite_2, SWT.NONE);
	lblMetric.setText("Metric");
    }
}
