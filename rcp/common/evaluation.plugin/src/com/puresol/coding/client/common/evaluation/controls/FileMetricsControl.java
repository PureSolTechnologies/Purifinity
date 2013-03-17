package com.puresol.coding.client.common.evaluation.controls;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.content.EvaluatorComboViewer;
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
    private final AnalyzedCode analyzedCode;

    private Composite centerComposite = null;

    public FileMetricsControl(Composite parent, int style,
	    AnalysisRun analysisRun, AnalyzedCode analyzedCode) {
	super(parent, style);
	this.analysisRun = analysisRun;
	this.analyzedCode = analyzedCode;

	setLayout(new FormLayout());

	Combo combo = new Combo(this, SWT.READ_ONLY);
	FormData fd_combo = new FormData();
	fd_combo.top = new FormAttachment(0, 10);
	fd_combo.right = new FormAttachment(100, -10);
	fd_combo.bottom = new FormAttachment(0, 10);
	fd_combo.left = new FormAttachment(0, 10);
	combo.setLayoutData(fd_combo);

	comboViewer = new EvaluatorComboViewer(combo);
	comboViewer.addSelectionChangedListener(this);

	metricDescriptionLabel = new Text(this, SWT.MULTI);
	FormData fd_metricDescriptionLabel = new FormData();
	fd_metricDescriptionLabel.top = new FormAttachment(combo, 33);
	fd_metricDescriptionLabel.bottom = new FormAttachment(100, -10);
	fd_metricDescriptionLabel.left = new FormAttachment(0, 10);
	fd_metricDescriptionLabel.right = new FormAttachment(100, -10);
	metricDescriptionLabel.setLayoutData(fd_metricDescriptionLabel);
	metricDescriptionLabel.setText("");
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
		if (centerComposite != null) {
		    centerComposite.dispose();
		}
		centerComposite = service.createFileResultComponent(this,
			analysisRun, analyzedCode);
		centerComposite.setLayoutData(BorderLayout.CENTER);
		layout();
	    }
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException(
		    "Could not lookup for evalutor result view!", e);
	}
    }
}
