package com.puresol.coding.client.common.evaluation;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.puresol.coding.client.common.evaluation.contents.CharacteristicsListViewer;
import com.puresol.coding.client.common.evaluation.contents.ParametersListViewer;
import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class EvaluatorInformationDialog extends Dialog {

    private Composite container;
    private Label nameLabel;
    private Text descriptionText;
    private List characteristicsList;
    private List parametersList;
    private ParametersListViewer parametersTableViewer;
    private CharacteristicsListViewer characteristicsListViewer;

    private EvaluatorFactory evaluatorFactory = null;

    /**
     * @wbp.parser.constructor
     */
    protected EvaluatorInformationDialog(Shell parentShell) {
	super(parentShell);
    }

    public EvaluatorInformationDialog(IShellProvider parentShell,
	    EvaluatorFactory evaluatorFactory) {
	super(parentShell);
	this.evaluatorFactory = evaluatorFactory;
	setShellStyle(SWT.TITLE | SWT.RESIZE);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
	container = new Composite(parent, SWT.BORDER);
	container.setLayout(new FormLayout());
	container.setSize(640, 200);

	nameLabel = new Label(container, SWT.NONE);
	FormData fdNameLabel = new FormData();
	fdNameLabel.left = new FormAttachment(0, 10);
	fdNameLabel.right = new FormAttachment(100, -10);
	fdNameLabel.top = new FormAttachment(0, 10);
	nameLabel.setLayoutData(fdNameLabel);
	nameLabel.setText("");

	descriptionText = new Text(container, SWT.H_SCROLL | SWT.V_SCROLL);
	descriptionText.setEditable(false);
	FormData fdDescriptionText = new FormData();
	fdDescriptionText.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
	fdDescriptionText.right = new FormAttachment(nameLabel, 0, SWT.RIGHT);
	fdDescriptionText.top = new FormAttachment(nameLabel, 10);
	descriptionText.setLayoutData(fdDescriptionText);
	descriptionText.setText("");
	descriptionText.setSize(SWT.DEFAULT, 100);

	Group characteristicsGroup = new Group(container, SWT.NONE);
	{
	    characteristicsGroup.setText("Related Quality Characteristics");
	    characteristicsGroup.setLayout(new FillLayout());
	    FormData fdCharacteristicsGroup = new FormData();
	    fdCharacteristicsGroup.left = new FormAttachment(descriptionText,
		    0, SWT.LEFT);
	    fdCharacteristicsGroup.right = new FormAttachment(descriptionText,
		    0, SWT.RIGHT);
	    fdCharacteristicsGroup.top = new FormAttachment(descriptionText, 10);
	    characteristicsGroup.setLayoutData(fdCharacteristicsGroup);

	    characteristicsList = new List(characteristicsGroup, SWT.NONE);
	    characteristicsListViewer = new CharacteristicsListViewer(
		    characteristicsList);
	}

	Group parametersGroup = new Group(container, SWT.NONE);
	{
	    parametersGroup.setText("Calculated Parameters");
	    parametersGroup.setLayout(new FillLayout());
	    FormData fdParametersGroup = new FormData();
	    fdParametersGroup.left = new FormAttachment(characteristicsGroup,
		    0, SWT.LEFT);
	    fdParametersGroup.right = new FormAttachment(characteristicsGroup,
		    0, SWT.RIGHT);
	    fdParametersGroup.top = new FormAttachment(characteristicsGroup, 10);
	    fdParametersGroup.bottom = new FormAttachment(100, -10);
	    parametersGroup.setLayoutData(fdParametersGroup);

	    parametersList = new List(parametersGroup, SWT.NONE);
	    parametersTableViewer = new ParametersListViewer(parametersList);
	}

	setEvaluatorFactory(evaluatorFactory);

	container.pack();
	Shell shell = getShell();
	shell.setSize(shell.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	return container;
    }

    @Override
    protected final void configureShell(Shell newShell) {
	super.configureShell(newShell);
	newShell.setText("Detailed Information for Evaluator");
    }

    public void setEvaluatorFactory(EvaluatorFactory evaluatorFactory) {
	this.evaluatorFactory = evaluatorFactory;
	if (evaluatorFactory != null) {
	    nameLabel.setText(evaluatorFactory.getName());
	    String description = evaluatorFactory.getDescription();
	    StringBuffer buffer = new StringBuffer();
	    int lineLength = 0;
	    for (String token : description.split(" ")) {
		if (lineLength + token.length() + 1 > 64) {
		    buffer.append("\n");
		    lineLength = 0;
		} else if (lineLength > 0) {
		    buffer.append(" ");
		    lineLength++;
		}
		buffer.append(token);
		lineLength += token.length();
	    }
	    descriptionText.setText(buffer.toString());
	    parametersTableViewer.setInput(evaluatorFactory.getParameters());
	    characteristicsListViewer.setInput(evaluatorFactory
		    .getEvaluatedQualityCharacteristics());
	    container.pack();
	    Shell shell = getShell();
	    shell.setSize(shell.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
    }

    @Override
    protected final void createButtonsForButtonBar(Composite parent) {
	createButton(parent, IDialogConstants.CLOSE_ID,
		IDialogConstants.CLOSE_LABEL, true);
    }

    @Override
    protected final void buttonPressed(int buttonId) {
	if (buttonId == IDialogConstants.CLOSE_ID) {
	    close();
	}
    }

}
