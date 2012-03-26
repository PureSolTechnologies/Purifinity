package com.puresol.coding.client.commands;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.utils.PreferencesUtils;
import com.puresol.utils.FileSearchConfiguration;

public class OpenAnalysis extends AbstractHandler implements IHandler {

    private static final ILog logger = Activator.getDefault().getLog();

    private static final String LAST_OPEN_ANALYSIS_PROJECT_DIRECTORY = "lastOpenAnalysisProjectDirectory";

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

    @Override
    public void dispose() {
	// not needed...
    }

    @Override
    public String execute(ExecutionEvent event) throws ExecutionException {
	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();

	DirectoryDialog fileDialog = new DirectoryDialog(shell, SWT.OPEN);
	fileDialog.setText("Open Analysis");
	fileDialog.setFilterPath(getLastProjectDirectory());
	String directory = fileDialog.open();
	if (directory != null) {
	    setLastProjectDirectory(directory);
	    IPreferenceStore preferenceStore = Activator.getDefault()
		    .getPreferenceStore();
	    FileSearchConfiguration searchConfiguration = PreferencesUtils
		    .getFileSearchConfiguration(preferenceStore);
	    ProjectAnalyzer projectAnalyzer = ProjectAnalyzerFactory.open(
		    new File(directory), searchConfiguration);
	    projectAnalyzer.schedule();
	}
	return directory;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public boolean isHandled() {
	return true;
    }

    private String getLastProjectDirectory() {
	IEclipsePreferences preferences = ConfigurationScope.INSTANCE
		.getNode("Code Analysis");
	Preferences newAnalysisNode = preferences.node("New Analysis");
	return newAnalysisNode.get(LAST_OPEN_ANALYSIS_PROJECT_DIRECTORY,
		System.getProperty("user.home", ""));
    }

    private void setLastProjectDirectory(String directory) {
	try {
	    IEclipsePreferences preferences = ConfigurationScope.INSTANCE
		    .getNode("Code Analysis");
	    Preferences newAnalysisNode = preferences.node("New Analysis");
	    newAnalysisNode.put(LAST_OPEN_ANALYSIS_PROJECT_DIRECTORY, directory);
	    preferences.flush();
	} catch (BackingStoreException e) {
	    logger.log(new Status(IStatus.ERROR, Activator.getDefault()
		    .getBundle().getSymbolicName(), e.getMessage(), e));
	}
    }

    @Override
    public void removeHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

}
