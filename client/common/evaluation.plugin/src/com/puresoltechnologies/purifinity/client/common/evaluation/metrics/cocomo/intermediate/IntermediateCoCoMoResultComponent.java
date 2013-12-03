package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import static com.puresoltechnologies.purifinity.client.common.ui.SWTUtils.DEFAULT_MARGIN;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoResults;

public class IntermediateCoCoMoResultComponent extends Composite implements
		AttributeChangedListener {

	private ProjectSettingsControl projectSettings;
	private IntermediateCoCoMoResultPanel resultPanel;
	private IntermediateCoCoMoResults results;
	private ProductAttributesContol productAttributes;
	private ProjectAttributesControl projectAttributes;
	private HardwareAttributesControl hardwareAttributes;
	private PersonellAttributesControl personellAttributes;

	public IntermediateCoCoMoResultComponent(Composite parent) {
		super(parent, SWT.NONE);

		FormLayout layout = new FormLayout();
		layout.marginWidth = DEFAULT_MARGIN;
		layout.marginHeight = DEFAULT_MARGIN;
		setLayout(layout);

		createAttributesPanel(projectSettings);
	}

	private void createAttributesPanel(Composite parameterSelection) {
		Composite attributesPanel = new Composite(this, SWT.NONE);
		FormData fdAttributesPanel = new FormData();
		fdAttributesPanel.top = new FormAttachment(parameterSelection,
				DEFAULT_MARGIN);
		fdAttributesPanel.left = new FormAttachment(0, DEFAULT_MARGIN);
		fdAttributesPanel.right = new FormAttachment(100, -DEFAULT_MARGIN);
		attributesPanel.setLayoutData(fdAttributesPanel);
		GridLayout layout = new GridLayout(2, true);
		attributesPanel.setLayout(layout);

		addProjectSettingsPanel(attributesPanel);
		addResultsPanel(attributesPanel);
		addProductAttributes(attributesPanel);
		addHardwareAttributes(attributesPanel);
		addPersonellAttributes(attributesPanel);
		addProjectAttributes(attributesPanel);
	}

	private void addProjectSettingsPanel(Composite attributesPanel) {
		projectSettings = new ProjectSettingsControl(attributesPanel,
				SWT.SHADOW_IN);
		projectSettings.addAttributeChangedListener(this);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		projectSettings.setLayoutData(gridData);
	}

	private void addProductAttributes(Composite attributesPanel) {
		productAttributes = new ProductAttributesContol(attributesPanel,
				SWT.SHADOW_IN);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		productAttributes.setLayoutData(gridData);
		productAttributes.addAttributeChangedListener(this);
	}

	private void addHardwareAttributes(Composite attributesPanel) {
		hardwareAttributes = new HardwareAttributesControl(attributesPanel,
				SWT.SHADOW_IN);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		hardwareAttributes.setLayoutData(gridData);
		hardwareAttributes.addAttributeChangedListener(this);
	}

	private void addPersonellAttributes(Composite attributesPanel) {
		personellAttributes = new PersonellAttributesControl(attributesPanel,
				SWT.SHADOW_IN);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		personellAttributes.setLayoutData(gridData);
		personellAttributes.addAttributeChangedListener(this);
	}

	private void addProjectAttributes(Composite attributesPanel) {
		projectAttributes = new ProjectAttributesControl(attributesPanel,
				SWT.SHADOW_IN);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		projectAttributes.setLayoutData(gridData);
		projectAttributes.addAttributeChangedListener(this);
	}

	private void addResultsPanel(Composite attributesPanel) {
		resultPanel = new IntermediateCoCoMoResultPanel(attributesPanel);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		resultPanel.setLayoutData(gridData);
	}

	@Override
	public void dispose() {
		super.dispose();
		resultPanel.dispose();
	}

	public IntermediateCoCoMoResults getResults() {
		return results;
	}

	public void setResults(IntermediateCoCoMoResults results) {
		this.results = results;
		projectSettings.setResults(results);
		productAttributes.setResults(results);
		hardwareAttributes.setResults(results);
		projectAttributes.setResults(results);
		personellAttributes.setResults(results);
		refresh();
	}

	private void refresh() {
		resultPanel.setResults(results);
	}

	@Override
	public void attributeChanged(Widget source) {
		if (results != null) {
			refresh();
		}
	}
}
