package com.puresol.coding.richclient.application.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.internal.workbench.WorkbenchLogger;

import com.puresoltechnologies.coding.analysis.api.Analysis;
import com.puresoltechnologies.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.coding.analysis.api.AnalysisSettings;
import com.puresoltechnologies.coding.analysis.api.AnalysisStore;
import com.puresoltechnologies.coding.analysis.api.AnalysisStoreFactory;
import com.puresoltechnologies.coding.analysis.api.ModuleStoreException;
import com.puresoltechnologies.coding.analysis.api.RepositoryLocation;
import com.puresoltechnologies.coding.richclient.application.utils.PreferencesUtils;
import com.puresoltechnologies.utils.FileSearchConfiguration;

@SuppressWarnings("restriction")
public class NewAnalysisJob extends Job {

	private final Logger logger = new WorkbenchLogger(
			NewAnalysisJob.class.getName());

	private final FileSearchConfiguration searchConfiguration;
	private final RepositoryLocation repositoryLocation;
	private final String description;
	private Analysis analysis = null;

	public NewAnalysisJob(String name, String description,
			RepositoryLocation repositoryLocation) {
		super(name);

		this.repositoryLocation = repositoryLocation;
		this.description = description;
		searchConfiguration = PreferencesUtils
				.getFileSearchConfiguration(InstanceScope.INSTANCE
						.getNode("/analysis"));
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			monitor.beginTask("Analysis of '" + getName() + "'", 1);
			AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
					.getInstance();
			AnalysisSettings analysisSettings = new AnalysisSettings(getName(),
					description, searchConfiguration, repositoryLocation);
			analysis = analysisStore.createAnalysis(analysisSettings);
			AnalysisRun analysisRun = analysis.runAnalysis();
			monitor.done();
			EvaluationJob job = new EvaluationJob(analysisRun);
			job.schedule();
			return Status.OK_STATUS;
		} catch (OperationCanceledException e) {
			logger.error("Analysis was cancelled!", e);
			return Status.CANCEL_STATUS;
		} catch (ModuleStoreException e) {
			logger.error("Error in directory store!", e);
			return Status.CANCEL_STATUS;
		} catch (InterruptedException e) {
			logger.error("Analysis was interrupted!", e);
			return Status.CANCEL_STATUS;
		}
	}

	public Analysis getAnalysis() {
		return analysis;
	}
}
