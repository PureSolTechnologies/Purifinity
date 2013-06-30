package com.puresol.purifinity.client.common.evaluation.metrics.cocomo;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.purifinity.coding.metrics.cocomo.CoCoMoResults;
import com.puresol.purifinity.coding.metrics.cocomo.Complexity;

public class CoCoMoResultComponent extends Composite implements ModifyListener,
		SelectionListener {

	private final Font font;
	private final Text text;
	private final Combo complexityCombo;
	private final ComboViewer complexityViewer;
	private final Text avgSalary;
	private final Text currency;
	private CoCoMoResults results;

	public CoCoMoResultComponent(Composite parent, int style) {
		super(parent, style);

		this.font = new Font(getDisplay(),
				new FontData("Courier", 12, SWT.NONE));

		Composite parameterSelection = new Composite(this, SWT.NONE);
		parameterSelection.setLayout(new RowLayout());

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

		text = new Text(this, SWT.BORDER | SWT.V_SCROLL);
		text.setFont(font);
		text.setEditable(false);
	}

	@Override
	public void dispose() {
		super.dispose();
		font.dispose();
	}

	public CoCoMoResults getResults() {
		return results;
	}

	public void setResults(CoCoMoResults results) {
		this.results = results;
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
		refresh();
	}

	private void refresh() {
		text.setText(results.toString());
	}

	@Override
	public void modifyText(ModifyEvent e) {
		if ((e.getSource() == avgSalary) || (e.getSource() == currency)) {
			try {
				results.setAverageSalary(Double.valueOf(avgSalary.getText()),
						currency.getText());
				refresh();
			} catch (NumberFormatException e1) {

			}
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == complexityCombo) {
			results.setComplexity(Complexity.values()[complexityCombo
					.getSelectionIndex()]);
			refresh();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		if (e.getSource() == complexityCombo) {
			results.setComplexity(Complexity.values()[complexityCombo
					.getSelectionIndex()]);
			refresh();
		}
	}
}
