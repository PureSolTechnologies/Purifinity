package com.puresol.coding.client.controls;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.content.EvaluatorComboViewer;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileMetricsControl extends Composite implements
	ISelectionChangedListener {

    private final EvaluatorComboViewer comboViewer;
    private final Text metricDescriptionLabel;
    private final AnalysisRun analysisRun;
    private final AnalyzedFile analyzedFile;

    public FileMetricsControl(Composite parent, int style,
	    AnalysisRun analysisRun, AnalyzedFile analyzedFile) {
	super(parent, style);
	this.analysisRun = analysisRun;
	this.analyzedFile = analyzedFile;

	setLayout(new BorderLayout());

	Composite selection = new Composite(this, SWT.NONE);
	selection.setLayoutData(BorderLayout.NORTH);
	selection.setLayout(new RowLayout());

	Combo combo = new Combo(selection, SWT.READ_ONLY);
	combo.setLayoutData(new RowData(SWT.DEFAULT, SWT.DEFAULT));

	comboViewer = new EvaluatorComboViewer(combo);
	comboViewer.addSelectionChangedListener(this);

	metricDescriptionLabel = new Text(selection, SWT.MULTI);
	metricDescriptionLabel.setText("");
	metricDescriptionLabel.setLayoutData(new RowData(SWT.DEFAULT,
		SWT.DEFAULT));
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
	if (event.getSource() == comboViewer) {
	    showEvaluation(event);
	}
    }

    /**
     * @param event
     */
    private void showEvaluation(SelectionChangedEvent event) {
	try {
	    StructuredSelection selection = (StructuredSelection) event
		    .getSelection();
	    EvaluatorFactory evaluatorFactory = (EvaluatorFactory) selection
		    .getFirstElement();
	    metricDescriptionLabel.setText(evaluatorFactory.getDescription());
	    metricDescriptionLabel.setSize(metricDescriptionLabel.computeSize(
		    SWT.DEFAULT, SWT.DEFAULT));
	    Evaluator evaluator = evaluatorFactory.create(analysisRun);
	    BundleContext bundleContext = Activator.getDefault().getBundle()
		    .getBundleContext();
	    String filter = "(evaluator=" + evaluator.getClass().getName()
		    + ")";
	    Collection<ServiceReference<EvaluatorGUIFactory>> serviceReferences = bundleContext
		    .getServiceReferences(EvaluatorGUIFactory.class, filter);

	    Iterator<ServiceReference<EvaluatorGUIFactory>> iterator = serviceReferences
		    .iterator();
	    if (iterator.hasNext()) {
		EvaluatorGUIFactory service = bundleContext.getService(iterator
			.next());
		Composite fileResultComponent = service
			.createFileResultComponent(this, analysisRun,
				analyzedFile);
		fileResultComponent.setLayoutData(BorderLayout.CENTER);
		layout();
	    }
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException(
		    "Could not lookup for evalutor result view!", e);
	}
    }
}
