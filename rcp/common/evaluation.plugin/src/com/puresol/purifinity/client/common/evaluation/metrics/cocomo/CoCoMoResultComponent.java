package com.puresol.purifinity.client.common.evaluation.metrics.cocomo;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoResults;
import com.puresol.purifinity.coding.metrics.cocomo.Complexity;

public class CoCoMoResultComponent extends Composite implements ModifyListener,
		SelectionListener {

	private final CoCoMoResultPanel resultPanel;
	private final Combo complexityCombo;
	private final ComboViewer complexityViewer;
	private final Text avgSalary;
	private final Text currency;
	private CoCoMoResults results;

	public CoCoMoResultComponent(Composite parent) {
		super(parent, SWT.NONE);

		FormLayout layout = new FormLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		setLayout(layout);

		Composite parameterSelection = new Composite(this, SWT.NONE);
		parameterSelection.setLayout(new GridLayout(2, true));
		FormData fdParameterSelection = new FormData();
		fdParameterSelection.left = new FormAttachment(0, 10);
		fdParameterSelection.right = new FormAttachment(100, -10);
		fdParameterSelection.top = new FormAttachment(0, 10);
		parameterSelection.setLayoutData(fdParameterSelection);

		new Label(parameterSelection, SWT.NONE).setText("Complexity:");
		complexityCombo = new Combo(parameterSelection, SWT.READ_ONLY);
		complexityCombo.addSelectionListener(this);
		complexityViewer = new ComboViewer(complexityCombo);
		complexityViewer.setContentProvider(ArrayContentProvider.getInstance());
		complexityViewer.setInput(Complexity.values());

		new Label(parameterSelection, SWT.NONE).setText("Avg. Salary:");
		avgSalary = new Text(parameterSelection, SWT.BORDER);
		avgSalary.addModifyListener(this);

		new Label(parameterSelection, SWT.NONE).setText("Currency:");
		currency = new Text(parameterSelection, SWT.BORDER);
		currency.addModifyListener(this);

		resultPanel = new CoCoMoResultPanel(this);
		FormData fdText = new FormData();
		fdText.left = new FormAttachment(0, 10);
		fdText.right = new FormAttachment(100, -10);
		fdText.top = new FormAttachment(parameterSelection, 10);
		fdText.bottom = new FormAttachment(100, -10);
		resultPanel.setLayoutData(fdText);
	}

	@Override
	public void dispose() {
		super.dispose();
		resultPanel.dispose();
	}

	public CoCoMoResults getResults() {
		return results;
	}

	public void setResults(CoCoMoResults results) {
		this.results = results;
		if (results != null) {
			String avgSalaray = String.valueOf(results.getAverageSalary());
			String currency2 = results.getCurrency();
			avgSalary.setText(avgSalaray);
			currency.setText(currency2);
			for (int i = 0; i < Complexity.values().length; i++) {
				Complexity complexity = Complexity.values()[i];
				if (complexity == results.getComplexity()) {
					complexityCombo.select(i);
				}
			}
		} else {
			avgSalary.setText("");
			currency.setText("");
		}
		refresh();
	}

	private void refresh() {
		resultPanel.setResults(results);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		if ((e.getSource() == avgSalary) || (e.getSource() == currency)) {
			try {
				if (results != null) {
					results.setAverageSalary(
							Double.valueOf(avgSalary.getText()),
							currency.getText());
					refresh();
				}
			} catch (NumberFormatException e1) {
			}
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == complexityCombo) {
			if (results != null) {
				results.setComplexity(Complexity.values()[complexityCombo
						.getSelectionIndex()]);
				refresh();
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		if (e.getSource() == complexityCombo) {
			if (results != null) {
				results.setComplexity(Complexity.values()[complexityCombo
						.getSelectionIndex()]);
				refresh();
			}
		}
	}
}
