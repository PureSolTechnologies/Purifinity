package com.puresol.coding.client.wizards;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;

import com.puresol.utils.FileSearch;

public class NewAnalysisWizard extends Wizard {

    private final NewAnalysisGeneralSettingsPage generalSettingsPage = new NewAnalysisGeneralSettingsPage();

    public NewAnalysisWizard() {
	super();
	setWindowTitle("New Analysis");
	addPage(generalSettingsPage);
    }

    @Override
    public boolean performFinish() {
	final String name = generalSettingsPage.getProjectName();
	String sourceDirectory = generalSettingsPage.getSourceDirectory();
	final List<File> files = FileSearch.find(new File(sourceDirectory),
		"**");
	Job job = new Job("Analysis for " + name) {

	    @Override
	    protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Analysis running " + name + "...",
			files.size());
		for (int i = 0; i < files.size(); i++) {
		    monitor.worked(1);
		    monitor.setTaskName("Analyse " + files.get(i) + "...");
		    try {
			Thread.sleep(100);
		    } catch (InterruptedException e) {
			monitor.done();
			return Status.CANCEL_STATUS;
		    }
		    if (monitor.isCanceled()) {
			monitor.done();
			return Status.CANCEL_STATUS;
		    }
		}
		monitor.done();
		return Status.OK_STATUS;
	    }
	};
	job.schedule();
	return true;
    }
}
