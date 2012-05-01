package com.puresol.coding.client.jobs;

import java.io.File;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.utils.PreferencesUtils;
import com.puresol.utils.FileSearchConfiguration;

public class NewAnalysisJob extends Job {

    private static final ILog logger = Activator.getDefault().getLog();

    private final FileSearchConfiguration searchConfiguration;
    private final File sourceDirectory;
    private final String description;
    private Analysis analysis = null;

    public NewAnalysisJob(String name, String description, File sourceDirectory) {
	super(name);
	this.sourceDirectory = sourceDirectory;
	this.description = description;
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
		    description, searchConfiguration, sourceDirectory);
	    analysis = analysisStore.createAnalysis(analysisSettings);
	    AnalysisRun analysisRun = analysis.runAnalysis();
	    monitor.done();
	    EvaluationJob job = new EvaluationJob(analysisRun);
	    job.schedule();
	    return Status.OK_STATUS;
	} catch (OperationCanceledException e) {
	    logger.log(new Status(Status.INFO, NewAnalysisJob.class.getName(),
		    "Analysis was cancelled!", e));
	    return Status.CANCEL_STATUS;
	} catch (DirectoryStoreException e) {
	    logger.log(new Status(Status.ERROR, NewAnalysisJob.class.getName(),
		    "Error in directory store!", e));
	    return Status.CANCEL_STATUS;
	} catch (InterruptedException e) {
	    logger.log(new Status(Status.ERROR, NewAnalysisJob.class.getName(),
		    "Analysis was interrupted!", e));
	    return Status.CANCEL_STATUS;
	}
    }

    public Analysis getAnalysis() {
	return analysis;
    }
}