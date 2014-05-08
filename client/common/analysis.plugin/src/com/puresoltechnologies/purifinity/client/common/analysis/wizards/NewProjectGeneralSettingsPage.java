package com.puresoltechnologies.purifinity.client.common.analysis.wizards;

import static com.puresoltechnologies.purifinity.client.common.ui.SWTUtils.DEFAULT_MARGIN;

import org.eclipse.core.runtime.ILog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.purifinity.client.common.analysis.Activator;

public class NewProjectGeneralSettingsPage extends WizardPage {

	public static final String LAST_NEW_ANALYSIS_SOURCE_DIRECTORY = "lastNewAnalysisSourceDirectory";

	private static final ILog log = Activator.getDefault().getLog();

	private Text projectName;
	private Text description;

	protected NewProjectGeneralSettingsPage() {
		super("General Settings");
		setTitle("General Settings");
		setMessage("Provide the general information for the new analysis project.");
	}

	@Override
	public void createControl(Composite parent) {
		setControl(createControlComposite(parent));
	}

	private Control createControlComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label projectNameLabel = new Label(composite, SWT.NONE);
		projectNameLabel.setText("Project Name:");
		FormData fdProjectNameLabel = new FormData();
		fdProjectNameLabel.top = new FormAttachment(0, DEFAULT_MARGIN);
		fdProjectNameLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdProjectNameLabel.right = new FormAttachment(100, -DEFAULT_MARGIN);
		projectNameLabel.setLayoutData(fdProjectNameLabel);

		projectName = new Text(composite, SWT.BORDER);
		FormData fdProjectName = new FormData();
		fdProjectName.top = new FormAttachment(projectNameLabel, DEFAULT_MARGIN);
		fdProjectName.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdProjectName.right = new FormAttachment(100, -DEFAULT_MARGIN);
		projectName.setLayoutData(fdProjectName);

		Label descriptionLabel = new Label(composite, SWT.NONE);
		descriptionLabel.setText("Description:");
		FormData fdDescriptionLabel = new FormData();
		fdDescriptionLabel.top = new FormAttachment(projectName, DEFAULT_MARGIN);
		fdDescriptionLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdDescriptionLabel.right = new FormAttachment(100, -DEFAULT_MARGIN);
		descriptionLabel.setLayoutData(fdDescriptionLabel);

		description = new Text(composite, SWT.BORDER);
		FormData fdDescription = new FormData();
		fdDescription.top = new FormAttachment(descriptionLabel, DEFAULT_MARGIN);
		fdDescription.bottom = new FormAttachment(100, -DEFAULT_MARGIN);
		fdDescription.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdDescription.right = new FormAttachment(100, -DEFAULT_MARGIN);
		description.setLayoutData(fdDescription);
		return composite;
	}

	public String getProjectName() {
		return projectName.getText();
	}

	public String getProjectDescription() {
		return description.getText();
	}
}
