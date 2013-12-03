package com.puresol.coding.richclient.application.controls;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.coding.analysis.api.AnalyzedCode;
import com.puresoltechnologies.coding.evaluation.api.Evaluator;
import com.puresoltechnologies.coding.evaluation.api.EvaluatorFactory;
import com.puresoltechnologies.coding.richclient.application.Activator;
import com.puresoltechnologies.coding.richclient.application.content.EvaluatorComboViewer;

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

		setLayout(new GridLayout());

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
			BundleContext bundleContext = Activator.getBundleContext();
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
