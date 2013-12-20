package com.puresoltechnologies.purifinity.client.lang.c11.prefs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.puresoltechnologies.purifinity.client.common.ui.controls.FileFilterGroup;
import com.puresoltechnologies.purifinity.client.lang.c11.Activator;
import com.puresoltechnologies.purifinity.framework.lang.c11.C11;

public class C11AnalysisPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private static final String FILES_INCLUDED = "C11.files.included";
	private static final String FILES_EXCLUDED = "C11.files.excluded";

	private static final String[] FILES_INCLUDED_DEFAULTS = C11.FILE_SUFFIXES;
	private static final String FILES_EXCLUDED_DEFAULTS = "";

	private FileFilterGroup fileFilterGroup;

	public C11AnalysisPreferencePage() {
		super();
	}

	public C11AnalysisPreferencePage(String title) {
		super(title);
	}

	public C11AnalysisPreferencePage(String title, ImageDescriptor image) {
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
		preferenceStore.setDefault(FILES_INCLUDED,
				getFilesIncludedDefaultText());
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

		fileFilterGroup = new FileFilterGroup(container, SWT.NONE);

		FormData fdFileFilterGroup = new FormData();
		fdFileFilterGroup.top = new FormAttachment(0);
		fdFileFilterGroup.left = new FormAttachment(0);
		fdFileFilterGroup.bottom = new FormAttachment(0, 256);
		fdFileFilterGroup.right = new FormAttachment(100);
		fileFilterGroup.setLayoutData(fdFileFilterGroup);

		setCurrentValues();

		return container;
	}

	private void setCurrentValues() {
		IPreferenceStore preferenceStore = getPreferenceStore();
		fileFilterGroup.setIncludes(preferenceStore.getString(FILES_INCLUDED));
		fileFilterGroup.setExcludes(preferenceStore.getString(FILES_EXCLUDED));
	}

	@Override
	public void performDefaults() {
		super.performDefaults();
		fileFilterGroup.setIncludes(getFilesIncludedDefaultText());
		fileFilterGroup.setExcludes(FILES_EXCLUDED_DEFAULTS);
	}

	@Override
	public boolean performOk() {
		IPreferenceStore preferenceStore = getPreferenceStore();
		preferenceStore.setValue(FILES_INCLUDED, fileFilterGroup.getIncludes());
		preferenceStore.setValue(FILES_EXCLUDED, fileFilterGroup.getExcludes());
		return super.performOk();
	}

}
