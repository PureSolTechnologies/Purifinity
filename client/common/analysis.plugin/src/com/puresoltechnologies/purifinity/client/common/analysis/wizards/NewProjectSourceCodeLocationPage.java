package com.puresoltechnologies.purifinity.client.common.analysis.wizards;

import static com.puresoltechnologies.purifinity.client.common.ui.SWTUtils.DEFAULT_MARGIN;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.RepositoryTypes;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisServiceClient;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class NewProjectSourceCodeLocationPage extends WizardPage {

	public static final String LAST_NEW_ANALYSIS_SOURCE_DIRECTORY = "lastNewAnalysisSourceDirectory";

	private static final ILog log = Activator.getDefault().getLog();

	private Text sourceDirectory;
	private final Set<RepositoryType> repositoryTypes = new LinkedHashSet<>();

	protected NewProjectSourceCodeLocationPage() {
		super("Source Code Location");
		setTitle("Source Code Location");
		setMessage("The location of the source code is provided.");
	}

	@Override
	public void createControl(Composite parent) {
		setControl(createControlComposite(parent));
	}

	private Control createControlComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label repositoryTypeLabel = new Label(composite, SWT.NONE);
		repositoryTypeLabel.setText("Repository Type:");
		FormData fdRepositoryTypeLabel = new FormData();
		fdRepositoryTypeLabel.top = new FormAttachment(0, DEFAULT_MARGIN);
		fdRepositoryTypeLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdRepositoryTypeLabel.right = new FormAttachment(100, -DEFAULT_MARGIN);
		repositoryTypeLabel.setLayoutData(fdRepositoryTypeLabel);

		Combo repositoryType = new Combo(composite, SWT.NONE);
		FormData fdRepositoryType = new FormData();
		fdRepositoryType.top = new FormAttachment(repositoryTypeLabel,
				DEFAULT_MARGIN);
		fdRepositoryType.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdRepositoryType.right = new FormAttachment(100, -DEFAULT_MARGIN);
		repositoryType.setLayoutData(fdRepositoryType);

		Label sourceDirectoryLabel = new Label(composite, SWT.NONE);
		sourceDirectoryLabel.setText("Source Directory:");
		FormData fdProjectName = new FormData();
		fdProjectName.top = new FormAttachment(repositoryType, DEFAULT_MARGIN);
		fdProjectName.left = new FormAttachment(0, DEFAULT_MARGIN);
		sourceDirectoryLabel.setLayoutData(fdProjectName);

		Button browseButton = new Button(composite, SWT.NONE);
		browseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				browse();
			}
		});
		browseButton.setText("Browse...");
		FormData fdBrowseButton = new FormData();
		fdBrowseButton.top = new FormAttachment(sourceDirectoryLabel, 0,
				SWT.TOP);
		fdBrowseButton.right = new FormAttachment(100, -DEFAULT_MARGIN);
		browseButton.setLayoutData(fdBrowseButton);

		fdProjectName.bottom = new FormAttachment(browseButton, 0, SWT.BOTTOM);
		sourceDirectoryLabel.setLayoutData(fdProjectName);

		sourceDirectory = new Text(composite, SWT.BORDER);
		FormData fdSourceDirectory = new FormData();
		fdSourceDirectory.top = new FormAttachment(sourceDirectoryLabel, 0,
				SWT.TOP);
		fdSourceDirectory.left = new FormAttachment(sourceDirectoryLabel,
				DEFAULT_MARGIN);
		fdSourceDirectory.right = new FormAttachment(browseButton,
				-DEFAULT_MARGIN, SWT.LEFT);
		fdSourceDirectory.bottom = new FormAttachment(browseButton, 0,
				SWT.BOTTOM);
		sourceDirectory.setLayoutData(fdSourceDirectory);

		try (AnalysisServiceClient client = new AnalysisServiceClient()) {
			RepositoryTypes repositoryTypes = client.getRepositoryTypes();
			this.repositoryTypes.addAll(repositoryTypes.getRepositoryTypes());
		} catch (IOException e) {
			throw new RuntimeException(
					"Could not read the available repository types.", e);
		}
		List<String> names = new ArrayList<>();
		for (RepositoryType type : repositoryTypes) {
			names.add(type.getName() + " '" + type.getDescription() + "'");
		}
		repositoryType.setItems(names.toArray(new String[names.size()]));

		return composite;
	}

	private void browse() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getShell();
		DirectoryDialog dialog = new DirectoryDialog(shell);

		String directory = getLastSourceDirectory();
		dialog.setFilterPath(directory);

		dialog.setText("Select Source Directory...");
		directory = dialog.open();
		if (directory != null) {
			sourceDirectory.setText(directory);
			setLastSourceDirectory(directory);
		}
	}

	private String getLastSourceDirectory() {
		IEclipsePreferences preferences = ConfigurationScope.INSTANCE
				.getNode("Code Analysis");
		Preferences newAnalysisNode = preferences.node("New Project");
		return newAnalysisNode.get(LAST_NEW_ANALYSIS_SOURCE_DIRECTORY,
				System.getProperty("user.home", ""));
	}

	private void setLastSourceDirectory(String directory) {
		try {
			IEclipsePreferences preferences = ConfigurationScope.INSTANCE
					.getNode("Code Analysis");
			Preferences newAnalysisNode = preferences.node("New Project");
			newAnalysisNode.put(LAST_NEW_ANALYSIS_SOURCE_DIRECTORY, directory);
			preferences.flush();
		} catch (BackingStoreException e) {
			log.log(new Status(IStatus.ERROR, Activator.getDefault()
					.getBundle().getSymbolicName(), e.getMessage(), e));
		}
	}

	public String getSourceDirectory() {
		return sourceDirectory.getText();
	}
}
