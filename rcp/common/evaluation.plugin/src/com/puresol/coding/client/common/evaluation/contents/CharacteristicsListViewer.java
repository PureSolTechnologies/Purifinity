package com.puresol.coding.client.common.evaluation.contents;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import com.puresol.coding.evaluation.api.QualityCharacteristic;

public class CharacteristicsListViewer extends ListViewer implements
	IStructuredContentProvider {

    private Set<QualityCharacteristic> parameters = new HashSet<QualityCharacteristic>();

    public CharacteristicsListViewer(Composite parent, int style) {
	super(parent, style);
	setContentProvider(this);
    }

    public CharacteristicsListViewer(Composite parent) {
	super(parent);
	setContentProvider(this);
    }

    public CharacteristicsListViewer(List list) {
	super(list);
	setContentProvider(this);
    }

    @Override
    public void dispose() {
	// intentionally left blank
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	@SuppressWarnings("unchecked")
	Set<QualityCharacteristic> input = (Set<QualityCharacteristic>) newInput;
	parameters = input;
    }

    @Override
    public QualityCharacteristic[] getElements(Object inputElement) {
	return parameters.toArray(new QualityCharacteristic[parameters.size()]);
    }

}
