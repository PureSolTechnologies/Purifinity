package com.puresol.coding.client.application.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.puresol.coding.client.application.Activator;

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

    private Text directoryIncludes = null;
    private Text directoryExcludes = null;
    private Text fileIncludes = null;
    private Text fileExcludes = null;
    private Label lblDirectoryExcludes;
    private Label lblFileIncludes;
    private Label lblFileExcludes;
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
	directoryIncludes.setText(DIRECTORY_INCLUDES_DEFAULT);
	directoryExcludes.setText(DIRECTORY_EXCLUDES_DEFAULT);
	fileIncludes.setText(FILE_INCLUDES_DEFAULT);
	fileExcludes.setText(FILE_EXCLUDES_DEFAULT);
	btnIgnoreHiddenFiles.setSelection(IGNORE_HIDDEN_DEFAULT);
    }

    @Override
    public boolean performOk() {
	IPreferenceStore preferenceStore = getPreferenceStore();
	preferenceStore.setValue(LOCATION_INCLUDES,
		directoryIncludes.getText());
	preferenceStore.setValue(LOCATION_EXCLUDES,
		directoryExcludes.getText());
	preferenceStore.setValue(FILE_INCLUDES, fileIncludes.getText());
	preferenceStore.setValue(FILE_EXCLUDES, fileExcludes.getText());
	preferenceStore.setValue(IGNORE_HIDDEN,
		btnIgnoreHiddenFiles.getSelection());
	return super.performOk();
    }

    @Override
    protected Control createContents(Composite parent) {
	Composite control = new Composite(parent, SWT.NONE);
	control.setLayout(new FormLayout());
	Label lblDirectoryIncludes = new Label(control, SWT.RIGHT);
	FormData fd_lblDirectoryIncludes = new FormData();
	fd_lblDirectoryIncludes.top = new FormAttachment(0, 39);
	fd_lblDirectoryIncludes.left = new FormAttachment(0, 5);
	lblDirectoryIncludes.setLayoutData(fd_lblDirectoryIncludes);
	lblDirectoryIncludes.setText("Directory includes:");

	directoryIncludes = new Text(control, SWT.BORDER | SWT.V_SCROLL
		| SWT.MULTI);
	FormData fd_directoryIncludes = new FormData();
	fd_directoryIncludes.bottom = new FormAttachment(0, 87);
	fd_directoryIncludes.right = new FormAttachment(0, 366);
	fd_directoryIncludes.top = new FormAttachment(0, 5);
	fd_directoryIncludes.left = new FormAttachment(0, 115);
	directoryIncludes.setLayoutData(fd_directoryIncludes);

	lblDirectoryExcludes = new Label(control, SWT.RIGHT);
	FormData fd_lblDirectoryExcludes = new FormData();
	fd_lblDirectoryExcludes.top = new FormAttachment(0, 126);
	fd_lblDirectoryExcludes.left = new FormAttachment(0, 5);
	lblDirectoryExcludes.setLayoutData(fd_lblDirectoryExcludes);
	lblDirectoryExcludes.setText("Directory excludes:");

	directoryExcludes = new Text(control, SWT.BORDER | SWT.V_SCROLL
		| SWT.MULTI);
	FormData fd_directoryExcludes = new FormData();
	fd_directoryExcludes.bottom = new FormAttachment(0, 174);
	fd_directoryExcludes.right = new FormAttachment(0, 366);
	fd_directoryExcludes.top = new FormAttachment(0, 92);
	fd_directoryExcludes.left = new FormAttachment(0, 115);
	directoryExcludes.setLayoutData(fd_directoryExcludes);
	lblFileIncludes = new Label(control, SWT.RIGHT);
	FormData fd_lblFileIncludes = new FormData();
	fd_lblFileIncludes.top = new FormAttachment(0, 213);
	fd_lblFileIncludes.left = new FormAttachment(0, 5);
	lblFileIncludes.setLayoutData(fd_lblFileIncludes);
	lblFileIncludes.setText("File includes:");

	fileIncludes = new Text(control, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
	FormData fd_fileIncludes = new FormData();
	fd_fileIncludes.bottom = new FormAttachment(0, 261);
	fd_fileIncludes.right = new FormAttachment(0, 366);
	fd_fileIncludes.top = new FormAttachment(0, 179);
	fd_fileIncludes.left = new FormAttachment(0, 115);
	fileIncludes.setLayoutData(fd_fileIncludes);

	lblFileExcludes = new Label(control, SWT.RIGHT);
	FormData fd_lblFileExcludes = new FormData();
	fd_lblFileExcludes.top = new FormAttachment(0, 301);
	fd_lblFileExcludes.left = new FormAttachment(0, 5);
	lblFileExcludes.setLayoutData(fd_lblFileExcludes);
	lblFileExcludes.setText("File excludes:");

	fileExcludes = new Text(control, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
	FormData fd_fileExcludes = new FormData();
	fd_fileExcludes.bottom = new FormAttachment(0, 350);
	fd_fileExcludes.right = new FormAttachment(0, 366);
	fd_fileExcludes.top = new FormAttachment(0, 266);
	fd_fileExcludes.left = new FormAttachment(0, 115);
	fileExcludes.setLayoutData(fd_fileExcludes);

	btnIgnoreHiddenFiles = new Button(control, SWT.CHECK);
	FormData fd_btnIgnoreHiddenFiles = new FormData();
	fd_btnIgnoreHiddenFiles.left = new FormAttachment(directoryIncludes, 0,
		SWT.LEFT);
	fd_btnIgnoreHiddenFiles.top = new FormAttachment(fileExcludes, 6);
	fd_btnIgnoreHiddenFiles.right = new FormAttachment(directoryIncludes,
		-32, SWT.RIGHT);
	btnIgnoreHiddenFiles.setLayoutData(fd_btnIgnoreHiddenFiles);
	btnIgnoreHiddenFiles.setText("Ignore hidden files and directories");

	setCurrentValues();

	return control;
    }

    private void setCurrentValues() {
	IPreferenceStore preferenceStore = getPreferenceStore();
	directoryIncludes.setText(preferenceStore
		.getString(NewAnalysisPreferencePage.LOCATION_INCLUDES));
	directoryExcludes.setText(preferenceStore
		.getString(NewAnalysisPreferencePage.LOCATION_EXCLUDES));
	fileIncludes.setText(preferenceStore
		.getString(NewAnalysisPreferencePage.FILE_INCLUDES));
	fileExcludes.setText(preferenceStore
		.getString(NewAnalysisPreferencePage.FILE_EXCLUDES));
	btnIgnoreHiddenFiles.setSelection(preferenceStore
		.getBoolean(IGNORE_HIDDEN));
    }
}
