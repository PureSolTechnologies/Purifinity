package com.puresol.coding.client.lang.fortran2008.prefs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.puresol.coding.client.lang.fortran2008.Activator;
import com.puresol.coding.lang.fortran.Fortran;

public class Fortran2008AnalysisPreferencePage extends PreferencePage implements
	IWorkbenchPreferencePage {

    private static final String FILES_INCLUDED = "Fortran2008.files.included";
    private static final String FILES_EXCLUDED = "Fortran2008.files.excluded";

    private static final String[] FILES_INCLUDED_DEFAULTS = Fortran.FILE_SUFFIXES;
    private static final String FILES_EXCLUDED_DEFAULTS = "";
    private Text excludes;
    private Text includes;

    /**
     * @wbp.parser.constructor
     */
    public Fortran2008AnalysisPreferencePage() {
	super();
    }

    public Fortran2008AnalysisPreferencePage(String title) {
	super(title);
    }

    public Fortran2008AnalysisPreferencePage(String title, ImageDescriptor image) {
	super(title, image);
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
	String filesIncludedText = getFilesIncludedDefaultText();
	preferenceStore.setDefault(FILES_INCLUDED, filesIncludedText);
	preferenceStore.setDefault(FILES_EXCLUDED, FILES_EXCLUDED_DEFAULTS);
    }

    private static String getFilesIncludedDefaultText() {
	StringBuilder builder = new StringBuilder();
	for (String suffix : FILES_INCLUDED_DEFAULTS) {
	    if (builder.length() > 0) {
		builder.append("\n");
	    }
	    builder.append("*" + suffix);
	}
	String filesIncludedText = builder.toString();
	return filesIncludedText;
    }

    @Override
    protected Control createContents(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	container.setLayout(new FormLayout());

	fileFilterGroup(container);
	setCurrentValues();

	return container;
    }

    private void fileFilterGroup(Composite container) {
	Group fileFilterGroup = new Group(container, SWT.NONE);
	fileFilterGroup.setText("File Filter");
	fileFilterGroup.setLayout(new FormLayout());
	{
	    FormData fdFileFilterGroup = new FormData();
	    fdFileFilterGroup.top = new FormAttachment(0);
	    fdFileFilterGroup.left = new FormAttachment(0);
	    fdFileFilterGroup.bottom = new FormAttachment(0, 256);
	    fdFileFilterGroup.right = new FormAttachment(100);
	    fileFilterGroup.setLayoutData(fdFileFilterGroup);
	}

	Label includesLabel = new Label(fileFilterGroup, SWT.NONE);
	FormData fd_includesLabel = new FormData();
	fd_includesLabel.left = new FormAttachment(0, 10);
	fd_includesLabel.top = new FormAttachment(0, 10);
	includesLabel.setLayoutData(fd_includesLabel);
	includesLabel.setText("Includes:");

	includes = new Text(fileFilterGroup, SWT.BORDER | SWT.V_SCROLL
		| SWT.MULTI);
	FormData fd_includes = new FormData();
	fd_includes.height = 120;
	fd_includes.left = new FormAttachment(0, 10);
	fd_includes.top = new FormAttachment(includesLabel, 6);
	fd_includes.right = new FormAttachment(100, -10);
	includes.setLayoutData(fd_includes);

	Label excludesLabel = new Label(fileFilterGroup, SWT.NONE);
	fd_includes.bottom = new FormAttachment(0, 120);
	FormData fd_excludesLabel = new FormData();
	fd_excludesLabel.left = new FormAttachment(0, 10);
	fd_excludesLabel.top = new FormAttachment(includes, 108);
	fd_excludesLabel.top = new FormAttachment(includes, 6);
	excludesLabel.setLayoutData(fd_excludesLabel);
	excludesLabel.setText("Excludes:");

	excludes = new Text(fileFilterGroup, SWT.BORDER | SWT.V_SCROLL
		| SWT.MULTI);
	FormData fd_excludes = new FormData();
	fd_excludes.height = 120;
	fd_excludes.left = new FormAttachment(0, 10);
	fd_excludes.top = new FormAttachment(excludesLabel, 6);
	fd_excludes.right = new FormAttachment(100, -10);
	fd_excludes.bottom = new FormAttachment(100, -10);
	excludes.setLayoutData(fd_excludes);
    }

    private void setCurrentValues() {
	IPreferenceStore preferenceStore = getPreferenceStore();
	includes.setText(preferenceStore.getString(FILES_INCLUDED));
	excludes.setText(preferenceStore.getString(FILES_EXCLUDED));
    }

    @Override
    public void performDefaults() {
	super.performDefaults();
	includes.setText(getFilesIncludedDefaultText());
	excludes.setText(FILES_EXCLUDED_DEFAULTS);
    }

    @Override
    public boolean performOk() {
	IPreferenceStore preferenceStore = getPreferenceStore();
	preferenceStore.setValue(FILES_INCLUDED, includes.getText());
	preferenceStore.setValue(FILES_EXCLUDED, excludes.getText());
	return super.performOk();
    }

}
