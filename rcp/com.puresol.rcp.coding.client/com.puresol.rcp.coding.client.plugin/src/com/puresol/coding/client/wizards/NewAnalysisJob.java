package com.puresol.coding.client.wizards;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.utils.PreferencesUtils;
import com.puresol.utils.FileSearchConfiguration;

public class NewAnalysisJob extends Job {

    private final FileSearchConfiguration searchConfiguration;
    private final File sourceDirectory;
    private Analysis analysis = null;

    public NewAnalysisJob(String name, File sourceDirectory) {
	super(name);
	this.sourceDirectory = sourceDirectory;
	IPreferenceStore preferenceStore = Activator.getDefault()
		.getPreferenceStore();
	searchConfiguration = PreferencesUtils
		.getFileSearchConfiguration(preferenceStore);
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	try {
	    monitor.beginTask("Analysis of '" + getName() + "'", 1);
	    AnalysisStore analysisStore = AnalysisStoreFactory.getInstance();
	    AnalysisSettings analysisSettings = new AnalysisSettings(getName(),
		    "<Not implemented, yet!>", searchConfiguration,
		    sourceDirectory);
	    analysis = analysisStore.createAnalysis(analysisSettings);
	    return Status.OK_STATUS;
	} catch (OperationCanceledException e) {
	    e.printStackTrace();
	    return Status.CANCEL_STATUS;
	} catch (AnalysisStoreException e) {
	    e.printStackTrace();
	    return Status.CANCEL_STATUS;
	}
    }

    public Analysis getAnalysis() {
	return analysis;
    }
}
