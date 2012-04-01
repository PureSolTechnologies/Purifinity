package com.puresol.coding.client.wizards;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.utils.PlatformUtils;
import com.puresol.coding.client.utils.PreferencesUtils;
import com.puresol.coding.evaluator.EvaluatorUtils;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.utils.FileSearchConfiguration;

public class NewAnalysisJob extends Job {

    private final FileSearchConfiguration searchConfiguration;
    private final File sourceDirectory;

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
	    IJobManager jobManager = Job.getJobManager();
	    List<ProjectEvaluatorFactory> projectEvaluatorFactories = EvaluatorUtils
		    .getProjectEvaluatorFactories();
	    monitor.beginTask("Analysis and Evaluation of '" + getName() + "'",
		    projectEvaluatorFactories.size() + 1);
	    ProjectAnalyzer job = ProjectAnalyzerFactory.create(getName(),
		    sourceDirectory,
		    new File(PlatformUtils.getWorkspaceDirectory(), getName()),
		    searchConfiguration);
	    job.setUser(true);
	    job.schedule();
	    job.join();

	    IProgressMonitor progressGroup = jobManager.createProgressGroup();
	    progressGroup.beginTask("Evaluation...",
		    projectEvaluatorFactories.size());
	    monitor.worked(1);
	    for (ProjectEvaluatorFactory factory : projectEvaluatorFactories) {
		ProjectEvaluator evaluatorJob = factory.create(job);
		evaluatorJob.setUser(true);
		evaluatorJob.setProgressGroup(progressGroup, 1);
		evaluatorJob.schedule();
	    }
	    progressGroup.done();
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (OperationCanceledException e) {
	    e.printStackTrace();
	    return Status.CANCEL_STATUS;
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    return Status.CANCEL_STATUS;
	}
    }
}
