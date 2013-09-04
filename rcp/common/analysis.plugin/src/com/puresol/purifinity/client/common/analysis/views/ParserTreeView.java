package com.puresol.purifinity.client.common.analysis.views;

import org.eclipse.core.runtime.ILog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.analysis.Activator;
import com.puresol.purifinity.client.common.analysis.controls.ParserTreeControl;
import com.puresol.purifinity.coding.analysis.api.AnalysisStore;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreFactory;

public class ParserTreeView extends ViewPart implements ISelectionListener {

	private static final ILog log = Activator.getDefault().getLog();

	private final AnalysisStore store = AnalysisStoreFactory.getFactory()
			.getInstance();
	private ParserTreeControl viewer;

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
		if (selection instanceof FileAnalysisSelection) {
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
