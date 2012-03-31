package com.puresol.coding.client.controls;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.puresol.coding.client.content.ProjectEvaluatorFactoryComboViewer;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MetricsControl extends Composite implements
	ISelectionChangedListener {

    private final ProjectEvaluatorFactoryComboViewer comboViewer;
    private final Text metricDescriptionLabel;

    public MetricsControl(Composite parent, int style) {
	super(parent, style);
	setLayout(new RowLayout());

	Combo combo = new Combo(this, SWT.READ_ONLY);
	combo.setLayoutData(new RowData(SWT.DEFAULT, SWT.DEFAULT));

	comboViewer = new ProjectEvaluatorFactoryComboViewer(combo);
	comboViewer.addSelectionChangedListener(this);

	metricDescriptionLabel = new Text(this, SWT.MULTI);
	metricDescriptionLabel.setText("");
	metricDescriptionLabel.setLayoutData(new RowData(SWT.DEFAULT,
		SWT.DEFAULT));
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
	if (event.getSource() == comboViewer) {
	    StructuredSelection selection = (StructuredSelection) event
		    .getSelection();
	    ProjectEvaluatorFactory factory = (ProjectEvaluatorFactory) selection
		    .getFirstElement();
	    metricDescriptionLabel.setText(factory.getDescription());
	    metricDescriptionLabel.setSize(metricDescriptionLabel.computeSize(
		    SWT.DEFAULT, SWT.DEFAULT));
	}
    }
}
