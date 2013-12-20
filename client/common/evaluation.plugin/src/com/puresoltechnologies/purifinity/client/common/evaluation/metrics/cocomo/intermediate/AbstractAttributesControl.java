package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.cocomo.intermediate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCOCOMOAttribute;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate.Rating;

public abstract class AbstractAttributesControl extends Composite implements
		SelectionListener {

	private final ListenerList listeners = new ListenerList();
	private final Group group;
	private final Map<IntermediateCOCOMOAttribute, Combo> combos = new HashMap<>();
	private IntermediateCoCoMoResults results = null;

	public AbstractAttributesControl(Composite parent, int style,
			String attributeGroupName) {
		super(parent, style);
		setLayout(new FillLayout());
		group = new Group(this, SWT.SHADOW_IN);
		group.setText(attributeGroupName);
		group.setLayout(new GridLayout(2, false));
	}

	protected void addAttributeCombo(IntermediateCOCOMOAttribute attribute) {
		new Label(group, SWT.NONE).setText(attribute.getName());
		Combo combo = new Combo(group, SWT.READ_ONLY);
		combo.addSelectionListener(this);
		combos.put(attribute, combo);

		List<String> items = new ArrayList<>();
		for (Rating rating : Rating.values()) {
			if (attribute.hasRating(rating)) {
				items.add(rating.name());
			}
		}
		combo.setItems(items.toArray(new String[items.size()]));
		combo.setText(Rating.NOMINAL.name());
	}

	public void setResults(IntermediateCoCoMoResults results) {
		this.results = results;
	}

	protected Map<IntermediateCOCOMOAttribute, Combo> getCombos() {
		return combos;
	}

	public void addAttributeChangedListener(AttributeChangedListener listener) {
		listeners.add(listener);
	}

	public void removeAttributeChangedListener(AttributeChangedListener listener) {
		listeners.remove(listener);
	}

	private void fireModifyEvent() {
		for (Object listener : listeners.getListeners()) {
			((AttributeChangedListener) listener).attributeChanged(this);
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (results == null) {
			return;
		}
		for (Entry<IntermediateCOCOMOAttribute, Combo> entry : combos
				.entrySet()) {
			Combo combo = entry.getValue();
			if (combo == e.getSource()) {
				IntermediateCOCOMOAttribute attribute = entry.getKey();
				results.setAttribute(attribute, Rating.valueOf(combo.getText()));
				fireModifyEvent();
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		for (Combo combo : combos.values()) {
			combo.setText(Rating.NOMINAL.name());
		}
	}
}
