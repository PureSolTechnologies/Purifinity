package com.puresol.coding.client.application.wizards;

import java.io.File;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.jobs.AnalysisJob;
import com.puresol.coding.client.application.utils.PreferencesUtils;
import com.puresol.utils.FileSearchConfiguration;

public class NewAnalysisWizard extends Wizard {

    private static final ILog logger = Activator.getDefault().getLog();

    private final NewAnalysisGeneralSettingsPage generalSettingsPage = new NewAnalysisGeneralSettingsPage();

    public NewAnalysisWizard() {
	super();
	setWindowTitle("New Analysis");
	addPage(generalSettingsPage);
    }

    @Override
    public boolean performFinish() {
	try {
	    String projectName = getProjectName();
	    String description = getDescription();
	    File sourceDirectory = getSourceDirectory();

	    IPreferenceStore preferenceStore = Activator.getDefault()
		    .getPreferenceStore();
	    FileSearchConfiguration searchConfiguration = PreferencesUtils
		    .getFileSearchConfiguration(preferenceStore);
	    AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
		    .getInstance();
	    AnalysisSettings analysisSettings = new AnalysisSettings(
		    projectName, description, searchConfiguration,
		    new DirectoryRepositoryLocation(projectName,
			    sourceDirectory));
	    AnalysisProject analysis = analysisStore.createAnalysis(analysisSettings);

	    AnalysisJob job = new AnalysisJob(analysis);
	    job.schedule();
	    return true;
	} catch (ModuleStoreException e) {
	    logger.log(new Status(Status.ERROR, Activator.getDefault()
		    .getBundle().getSymbolicName(),
		    "Could not create new analysis project.", e));
	    return false;
	}
    }

    public String getProjectName() {
	return generalSettingsPage.getProjectName();
    }

    public String getDescription() {
	return generalSettingsPage.getDescription();
    }

    public File getSourceDirectory() {
	return new File(generalSettingsPage.getSourceDirectory());
    }
}
