package com.puresoltechnologies.purifinity.client.common.analysis.wizards;

import static com.puresoltechnologies.purifinity.client.common.ui.SWTUtils.DEFAULT_MARGIN;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.RepositoryTypeComboViewer;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class NewProjectSourceCodeLocationPage extends WizardPage implements
		SelectionListener {

	public static final String LAST_NEW_ANALYSIS_SOURCE_DIRECTORY = "lastNewAnalysisSourceDirectory";

	private Composite composite;
	private Combo repositoryType;
	private RepositoryTypeComboViewer comboViewer;
	private final Map<String, Label> labels = new HashMap<>();
	private final Map<String, Text> controls = new HashMap<>();

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
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label repositoryTypeLabel = new Label(composite, SWT.NONE);
		repositoryTypeLabel.setText("Repository Type:");
		FormData fdRepositoryTypeLabel = new FormData();
		fdRepositoryTypeLabel.top = new FormAttachment(0, DEFAULT_MARGIN);
		fdRepositoryTypeLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdRepositoryTypeLabel.right = new FormAttachment(100, -DEFAULT_MARGIN);
		repositoryTypeLabel.setLayoutData(fdRepositoryTypeLabel);

		repositoryType = new Combo(composite, SWT.READ_ONLY);
		repositoryType.setText("please choose");
		FormData fdRepositoryType = new FormData();
		fdRepositoryType.top = new FormAttachment(repositoryTypeLabel,
				DEFAULT_MARGIN);
		fdRepositoryType.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdRepositoryType.right = new FormAttachment(100, -DEFAULT_MARGIN);
		repositoryType.setLayoutData(fdRepositoryType);

		comboViewer = new RepositoryTypeComboViewer(repositoryType);
		repositoryType.addSelectionListener(this);

		return composite;
	}

	public Properties getSourceLocationProperties() {
		Properties properties = new Properties();
		RepositoryType repositoryType = comboViewer.getSelectedRepositoryType();
		properties.put(RepositoryLocation.REPOSITORY_LOCATION_CLASS,
				repositoryType.getClassName());
		properties.setProperty(RepositoryLocation.REPOSITORY_LOCATION_NAME,
				repositoryType.getName());
		for (String parameterId : controls.keySet()) {
			Text text = controls.get(parameterId);
			properties.setProperty(parameterId, text.getText());
		}
		return properties;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == repositoryType) {
			RepositoryType type = comboViewer.getSelectedRepositoryType();
			selectionChange(type);
		}
	}

	private void selectionChange(RepositoryType type) {
		removeControls();
		if (type != null) {
			Control lastControl = repositoryType;
			Map<String, Parameter<?>> parameters = type.getParameters();
			for (String parameterId : parameters.keySet()) {
				Parameter<?> parameter = parameters.get(parameterId);
				Label label = new Label(composite, SWT.NONE);
				String labelText = parameter.getName();
				String unit = parameter.getUnit();
				if ((unit != null) && (!unit.isEmpty())) {
					labelText += " [" + unit + "]";
				}
				label.setText(labelText);
				FormData fdLabel = new FormData();
				fdLabel.top = new FormAttachment(lastControl, DEFAULT_MARGIN);
				fdLabel.left = new FormAttachment(0, DEFAULT_MARGIN);
				label.setLayoutData(fdLabel);

				Text control = new Text(composite, SWT.BORDER);
				control.setToolTipText(parameter.getDescription());
				FormData fdControl = new FormData();
				fdControl.top = new FormAttachment(label, 0, SWT.TOP);
				fdControl.left = new FormAttachment(label, DEFAULT_MARGIN);
				fdControl.right = new FormAttachment(100, -DEFAULT_MARGIN);
				control.setLayoutData(fdControl);

				labels.put(parameter.getName(), label);
				controls.put(parameter.getName(), control);

				lastControl = control;
			}
		}
		composite.layout(true);
	}

	private void removeControls() {
		for (Label label : labels.values()) {
			label.dispose();
		}
		for (Text control : controls.values()) {
			control.dispose();
		}
		labels.clear();
		controls.clear();
		composite.layout(true);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		repositoryType.select(0);
	}

}
