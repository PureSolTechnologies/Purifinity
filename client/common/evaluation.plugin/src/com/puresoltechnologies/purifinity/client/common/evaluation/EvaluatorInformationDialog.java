package com.puresoltechnologies.purifinity.client.common.evaluation;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.ParameterListViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.QualityCharacteristicListViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.controls.QualityCharacteristicInformationComponent;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.ui.controls.ParameterInformationComponent;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;

public class EvaluatorInformationDialog extends Dialog implements
		SelectionListener {

	private Composite container;
	private Label nameLabel;
	private Text descriptionText;
	private List characteristicsList;
	private List parametersList;
	private ParameterListViewer parametersListViewer;
	private QualityCharacteristicListViewer characteristicsListViewer;
	private QualityCharacteristicInformationComponent characteristicInformation;
	private ParameterInformationComponent parameterInformation;

	private EvaluatorFactory evaluatorFactory = null;

	/**
	 * @wbp.parser.constructor
	 */
	protected EvaluatorInformationDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.TITLE | SWT.RESIZE);
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

		nameLabel = new Label(container, SWT.NONE);
		FormData fdNameLabel = new FormData();
		fdNameLabel.left = new FormAttachment(0, 10);
		fdNameLabel.right = new FormAttachment(100, -10);
		fdNameLabel.top = new FormAttachment(0, 10);
		nameLabel.setLayoutData(fdNameLabel);

		descriptionText = new Text(container, SWT.H_SCROLL | SWT.V_SCROLL);
		descriptionText.setEditable(false);
		FormData fdDescriptionText = new FormData();
		fdDescriptionText.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
		fdDescriptionText.right = new FormAttachment(nameLabel, 0, SWT.RIGHT);
		fdDescriptionText.top = new FormAttachment(nameLabel, 10);
		descriptionText.setLayoutData(fdDescriptionText);
		descriptionText.setSize(SWT.DEFAULT, 100);

		Group characteristicsGroup = new Group(container, SWT.NONE);
		{
			characteristicsGroup.setText("Related Quality Characteristics");
			characteristicsGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
			FormData fdCharacteristicsGroup = new FormData();
			fdCharacteristicsGroup.left = new FormAttachment(descriptionText,
					0, SWT.LEFT);
			fdCharacteristicsGroup.right = new FormAttachment(descriptionText,
					0, SWT.RIGHT);
			fdCharacteristicsGroup.top = new FormAttachment(descriptionText, 10);
			characteristicsGroup.setLayoutData(fdCharacteristicsGroup);

			characteristicsList = new List(characteristicsGroup, SWT.V_SCROLL);
			characteristicsListViewer = new QualityCharacteristicListViewer(
					characteristicsList);
			characteristicsList.addSelectionListener(this);

			characteristicInformation = new QualityCharacteristicInformationComponent(
					characteristicsGroup, SWT.BORDER);
		}

		Group parametersGroup = new Group(container, SWT.NONE);
		{
			parametersGroup.setText("Calculated Parameters");
			parametersGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
			FormData fdParametersGroup = new FormData();
			fdParametersGroup.left = new FormAttachment(characteristicsGroup,
					0, SWT.LEFT);
			fdParametersGroup.right = new FormAttachment(characteristicsGroup,
					0, SWT.RIGHT);
			fdParametersGroup.top = new FormAttachment(characteristicsGroup, 10);
			fdParametersGroup.bottom = new FormAttachment(100, -10);
			parametersGroup.setLayoutData(fdParametersGroup);

			parametersList = new List(parametersGroup, SWT.V_SCROLL);
			parametersListViewer = new ParameterListViewer(parametersList);
			parametersList.addSelectionListener(this);

			parameterInformation = new ParameterInformationComponent(
					parametersGroup, SWT.BORDER);
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
			description = StringUtils.wrapLinesByWords(description, 64);
			descriptionText.setText(description);
			parametersListViewer.setInput(evaluatorFactory.getParameters());
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

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == parametersList) {
			Parameter<?> firstElement = (Parameter<?>) ((IStructuredSelection) parametersListViewer
					.getSelection()).getFirstElement();
			parameterInformation.setParameter(firstElement);
		} else if (e.getSource() == characteristicsList) {
			QualityCharacteristic firstElement = (QualityCharacteristic) ((IStructuredSelection) characteristicsListViewer
					.getSelection()).getFirstElement();
			characteristicInformation.setParameter(firstElement);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
