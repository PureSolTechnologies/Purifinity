package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.SoftwareProject;

public class ProjectSettingsControl extends Composite implements
		SelectionListener, ModifyListener {

	private final ListenerList listeners = new ListenerList();

	private final Group group;

	private final Combo softwareProjectCombo;
	private final ComboViewer softwareProjectViewer;
	private final Text avgSalary;
	private final Text currency;

	private IntermediateCoCoMoResults results = null;

	public ProjectSettingsControl(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		group = new Group(this, SWT.SHADOW_IN);
		group.setText("Project Settings");
		group.setLayout(new GridLayout(2, false));

		new Label(group, SWT.NONE).setText("Software Project:");
		softwareProjectCombo = new Combo(group, SWT.READ_ONLY);
		softwareProjectCombo.addSelectionListener(this);
		softwareProjectViewer = new ComboViewer(softwareProjectCombo);
		softwareProjectViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		softwareProjectViewer.setInput(SoftwareProject.values());

		new Label(group, SWT.NONE).setText("Avg. Salary:");
		avgSalary = new Text(group, SWT.BORDER);
		avgSalary.addModifyListener(this);

		new Label(group, SWT.NONE).setText("Currency:");
		currency = new Text(group, SWT.BORDER);
		currency.addModifyListener(this);
	}

	public IntermediateCoCoMoResults getResults() {
		return results;
	}

	public void setResults(IntermediateCoCoMoResults results) {
		this.results = results;
		if (results != null) {
			String avgSalaray = String.valueOf(results.getAverageSalary());
			String currency2 = results.getCurrency();
			avgSalary.setText(avgSalaray);
			currency.setText(currency2);
			for (int i = 0; i < SoftwareProject.values().length; i++) {
				SoftwareProject project = SoftwareProject.values()[i];
				if (project == results.getProject()) {
					softwareProjectCombo.select(i);
				}
			}
		} else {
			avgSalary.setText("");
			currency.setText("");
		}
	}

	@Override
	public void modifyText(ModifyEvent e) {
		if ((e.getSource() == avgSalary) || (e.getSource() == currency)) {
			try {
				if (results != null) {
					results.setAverageSalary(
							Double.valueOf(avgSalary.getText()),
							currency.getText());
					fireModifyEvent();
				}
			} catch (NumberFormatException e1) {
			}
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == softwareProjectCombo) {
			if (results != null) {
				results.setProject(SoftwareProject.values()[softwareProjectCombo
						.getSelectionIndex()]);
				fireModifyEvent();
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		if (e.getSource() == softwareProjectCombo) {
			if (results != null) {
				results.setProject(SoftwareProject.values()[softwareProjectCombo
						.getSelectionIndex()]);
				fireModifyEvent();
			}
		}
	}

	private void fireModifyEvent() {
		for (Object listener : listeners.getListeners()) {
			((AttributeChangedListener) listener).attributeChanged(this);
		}
	}

	public void addAttributeChangedListener(AttributeChangedListener listener) {
		listeners.add(listener);
	}

	public void removeModifyListener(ModifyListener listener) {
		listeners.remove(listener);
	}

	public SoftwareProject getSoftwareProject() {
		return SoftwareProject.values()[softwareProjectCombo
				.getSelectionIndex()];
	}

	public double getAvgSalary() {
		return Double.valueOf(avgSalary.getText());
	}

	public String getCurrency() {
		return currency.getText();
	}
}
