package com.puresoltechnologies.purifinity.client.common.analysis.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.ui.controls.FileFilterGroup;

public class NewAnalysisPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    public static final String LOCATION_INCLUDES = "NewAnalysis.directory.includes";
    public static final String LOCATION_EXCLUDES = "NewAnalysis.directory.excludes";
    public static final String FILE_INCLUDES = "NewAnalysis.file.includes";
    public static final String FILE_EXCLUDES = "NewAnalysis.file.excludes";
    public static final String IGNORE_HIDDEN = "NewAnalysis.ignore_hidden";

    private static final String DIRECTORY_INCLUDES_DEFAULT = "";
    private static final String DIRECTORY_EXCLUDES_DEFAULT = ".*\nbin\ntarget";
    private static final String FILE_INCLUDES_DEFAULT = "";
    private static final String FILE_EXCLUDES_DEFAULT = ".*\n*.bak\n*~";
    private static final boolean IGNORE_HIDDEN_DEFAULT = true;

    private FileFilterGroup fileFilterGroup;
    private FileFilterGroup directoryFilterGroup;
    private Button btnIgnoreHiddenFiles;

    /**
     * @wbp.parser.constructor
     */
    public NewAnalysisPreferencePage() {
	super("New Analysis Settings");
    }

    @Override
    public void init(IWorkbench workbench) {
	IPreferenceStore preferenceStore = Activator.getDefault()
		.getPreferenceStore();
	setPreferenceStore(preferenceStore);
	setDefaultValuesToPreferencesStore(preferenceStore);
    }

    public static void setDefaultValuesToPreferencesStore(
	    IPreferenceStore preferenceStore) {
	preferenceStore.setDefault(LOCATION_INCLUDES,
		DIRECTORY_INCLUDES_DEFAULT);
	preferenceStore.setDefault(LOCATION_EXCLUDES,
		DIRECTORY_EXCLUDES_DEFAULT);
	preferenceStore.setDefault(FILE_INCLUDES, FILE_INCLUDES_DEFAULT);
	preferenceStore.setDefault(FILE_EXCLUDES, FILE_EXCLUDES_DEFAULT);
	preferenceStore.setDefault(IGNORE_HIDDEN, IGNORE_HIDDEN_DEFAULT);
    }

    @Override
    public void performDefaults() {
	super.performDefaults();
	directoryFilterGroup.setIncludes(DIRECTORY_INCLUDES_DEFAULT);
	directoryFilterGroup.setExcludes(DIRECTORY_EXCLUDES_DEFAULT);
	fileFilterGroup.setIncludes(FILE_INCLUDES_DEFAULT);
	fileFilterGroup.setExcludes(FILE_EXCLUDES_DEFAULT);
	btnIgnoreHiddenFiles.setSelection(IGNORE_HIDDEN_DEFAULT);
    }

    @Override
    public boolean performOk() {
	IPreferenceStore preferenceStore = getPreferenceStore();
	preferenceStore.setValue(LOCATION_INCLUDES,
		directoryFilterGroup.getIncludes());
	preferenceStore.setValue(LOCATION_EXCLUDES,
		directoryFilterGroup.getExcludes());
	preferenceStore.setValue(FILE_INCLUDES, fileFilterGroup.getIncludes());
	preferenceStore.setValue(FILE_EXCLUDES, fileFilterGroup.getExcludes());
	preferenceStore.setValue(IGNORE_HIDDEN,
		btnIgnoreHiddenFiles.getSelection());
	return super.performOk();
    }

    @Override
    protected Control createContents(Composite parent) {
	Composite control = new Composite(parent, SWT.NONE);
	control.setLayout(new FormLayout());

	btnIgnoreHiddenFiles = new Button(control, SWT.CHECK);
	FormData fd_btnIgnoreHiddenFiles = new FormData();
	fd_btnIgnoreHiddenFiles.top = new FormAttachment(10);
	fd_btnIgnoreHiddenFiles.left = new FormAttachment(0);
	fd_btnIgnoreHiddenFiles.right = new FormAttachment(100);
	btnIgnoreHiddenFiles.setLayoutData(fd_btnIgnoreHiddenFiles);
	btnIgnoreHiddenFiles.setText("Ignore hidden files and directories");

	directoryFilterGroup = new FileFilterGroup(control, SWT.NONE, false);
	FormData fd_directoryFilterGroup = new FormData();
	fd_directoryFilterGroup.top = new FormAttachment(btnIgnoreHiddenFiles,
		10);
	fd_directoryFilterGroup.left = new FormAttachment(0);
	fd_directoryFilterGroup.right = new FormAttachment(100);
	directoryFilterGroup.setLayoutData(fd_directoryFilterGroup);

	fileFilterGroup = new FileFilterGroup(control, SWT.NONE, true);
	FormData fd_fileFilterGroup = new FormData();
	fd_fileFilterGroup.top = new FormAttachment(directoryFilterGroup, 10);
	fd_fileFilterGroup.left = new FormAttachment(0);
	fd_fileFilterGroup.right = new FormAttachment(100);
	fileFilterGroup.setLayoutData(fd_fileFilterGroup);

	setCurrentValues();

	return control;
    }

    private void setCurrentValues() {
	IPreferenceStore preferenceStore = getPreferenceStore();
	directoryFilterGroup.setIncludes(preferenceStore
		.getString(NewAnalysisPreferencePage.LOCATION_INCLUDES));
	directoryFilterGroup.setExcludes(preferenceStore
		.getString(NewAnalysisPreferencePage.LOCATION_EXCLUDES));
	fileFilterGroup.setIncludes(preferenceStore
		.getString(NewAnalysisPreferencePage.FILE_INCLUDES));
	fileFilterGroup.setExcludes(preferenceStore
		.getString(NewAnalysisPreferencePage.FILE_EXCLUDES));
	btnIgnoreHiddenFiles.setSelection(preferenceStore
		.getBoolean(IGNORE_HIDDEN));
    }
}
