package com.puresol.coding.richclient.application.wizards;

import javax.inject.Inject;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.puresol.coding.richclient.application.Activator;

@SuppressWarnings("restriction")
public class NewAnalysisGeneralSettingsPage extends WizardPage {

	public static final String LAST_NEW_ANALYSIS_SOURCE_DIRECTORY = "lastNewAnalysisSourceDirectory";

	@Inject
	private ILog log;

	@Inject
	private Shell shell;

	private Text textSourceDirectory;
	private Text textProjectName;
	private Text textDescription;

	protected NewAnalysisGeneralSettingsPage() {
		super("Source Directory");
		setTitle("General Settings");
		setMessage("Provide the general settings for the new analysis.");
	}

	@Override
	public void createControl(Composite parent) {
		setControl(createControlComposite(parent));

	}

	private Control createControlComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 1));

		Label lblProjectName = new Label(composite, SWT.NONE);
		lblProjectName.setText("Project Name:");

		textProjectName = new Text(composite, SWT.BORDER);
		textProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		new Label(composite, SWT.NONE);

		Label lblSourceDirectory = new Label(composite, SWT.NONE);
		lblSourceDirectory.setText("Source Directory:");

		textSourceDirectory = new Text(composite, SWT.BORDER);
		textSourceDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Button btnBrowse = new Button(composite, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				browse();
			}
		});
		btnBrowse.setText("Browse...");

		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText("Description:");

		textDescription = new Text(composite, SWT.BORDER);
		textDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		new Label(composite, SWT.NONE);
		return composite;
	}

	private void browse() {
		DirectoryDialog dialog = new DirectoryDialog(shell);

		String directory = getLastSourceDirectory();
		dialog.setFilterPath(directory);

		dialog.setText("Select Source Directory...");
		directory = dialog.open();
		if (directory != null) {
			textSourceDirectory.setText(directory);
			setLastSourceDirectory(directory);
		}
	}

	private String getLastSourceDirectory() {
		IEclipsePreferences preferences = ConfigurationScope.INSTANCE
				.getNode("Code Analysis");
		Preferences newAnalysisNode = preferences.node("New Analysis");
		return newAnalysisNode.get(LAST_NEW_ANALYSIS_SOURCE_DIRECTORY,
				System.getProperty("user.home", ""));
	}

	private void setLastSourceDirectory(String directory) {
		try {
			IEclipsePreferences preferences = ConfigurationScope.INSTANCE
					.getNode("Code Analysis");
			Preferences newAnalysisNode = preferences.node("New Analysis");
			newAnalysisNode.put(LAST_NEW_ANALYSIS_SOURCE_DIRECTORY, directory);
			preferences.flush();
		} catch (BackingStoreException e) {
			log.log(new Status(IStatus.ERROR, Activator.getBundleContext()
					.getBundle().getSymbolicName(), e.getMessage(), e));
		}
	}

	public String getProjectName() {
		return textProjectName.getText();
	}

	public String getProjectDescription() {
		return textDescription.getText();
	}

	public String getSourceDirectory() {
		return textSourceDirectory.getText();
	}
}
