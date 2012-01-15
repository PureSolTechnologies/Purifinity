package com.puresol.coding.client.wizards;

import javax.i18n4java.Translator;

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
import org.eclipse.ui.PlatformUI;

public class NewAnalysisGeneralSettingsPage extends WizardPage {

    private static final Translator translator = Translator
	    .getTranslator(NewAnalysisGeneralSettingsPage.class);

    private Text textSourceDirectory;
    private Text textProjectName;

    protected NewAnalysisGeneralSettingsPage() {
	super("Source Directory");
	setTitle(translator.i18n("General Settings"));
	setMessage(translator
		.i18n("Provide the general settings for the new analysis."));
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
	return composite;
    }

    private void browse() {
	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();
	DirectoryDialog dialog = new DirectoryDialog(shell);
	dialog.setText("Select Source Directory...");
	String directory = dialog.open();
	if (directory != null) {
	    textSourceDirectory.setText(directory);
	}
    }
}
