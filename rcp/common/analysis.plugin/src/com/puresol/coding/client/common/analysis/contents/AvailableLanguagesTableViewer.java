package com.puresol.coding.client.common.analysis.contents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;

public class AvailableLanguagesTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<AnalyzableProgrammingLanguage> languages = new ArrayList<AnalyzableProgrammingLanguage>();

	public AvailableLanguagesTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		setupNameColumn();
		setupVersionColumn();
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalyzableProgrammingLanguage language = (AnalyzableProgrammingLanguage) element;
				return language.getName();
			}
		});
	}

	private void setupVersionColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Version");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalyzableProgrammingLanguage language = (AnalyzableProgrammingLanguage) element;
				return language.getVersion();
			}
		});
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		languages.clear();
		if (newInput == null) {
			return;
		}
		if (Collection.class.isAssignableFrom(newInput.getClass())) {
			@SuppressWarnings("unchecked")
			Collection<AnalyzableProgrammingLanguage> collection = (Collection<AnalyzableProgrammingLanguage>) newInput;
			languages.addAll(collection);
		}
		refresh();
	}

	@Override
	public AnalyzableProgrammingLanguage[] getElements(Object inputElement) {
		return languages.toArray(new AnalyzableProgrammingLanguage[languages
				.size()]);
	}

}
