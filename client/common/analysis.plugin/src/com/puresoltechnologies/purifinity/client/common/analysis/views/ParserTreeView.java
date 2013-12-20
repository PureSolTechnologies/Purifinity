package com.puresoltechnologies.purifinity.client.common.analysis.views;

import org.eclipse.core.runtime.ILog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisStore;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.controls.ParserTreeControl;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisStoreFactory;

public class ParserTreeView extends AbstractPureSolTechnologiesView implements
		ISelectionListener {

	private static final ILog log = Activator.getDefault().getLog();

	private final AnalysisStore store = AnalysisStoreFactory.getFactory()
			.getInstance();
	private ParserTreeControl viewer;

	public ParserTreeView() {
		super(Activator.getDefault());
	}

	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		viewer = new ParserTreeControl(parent);
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		viewer.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// try {
		if (selection instanceof AnalysisSelection) {
			// AnalysisSelection analysisSelection = (AnalysisSelection)
			// selection;
			// AnalysisInformation information = analysisSelection
			// .getInformation();
			//
			// Analysis analysis =
			// store.loadAnalysis(information.getUUID());
			//
			// AnalyzedFile analyzedFile = analysis.loadLastAnalysisRun()
			// .findAnalyzedFile(sourceFile);
			// viewer.setContentAndUpdateContent(analyzedFile, analysis);
		}
		// } catch (IOException e) {
		// log.log(new Status(Status.ERROR, Activator.getDefault().getBundle()
		// .getSymbolicName(), e.getMessage(), e));
		// } catch (AnalysisStoreException e) {
		// log.log(new Status(Status.ERROR, ParserTreeControl.class.getName(),
		// "Can not read analysis store!", e));
		// }
	}
}
