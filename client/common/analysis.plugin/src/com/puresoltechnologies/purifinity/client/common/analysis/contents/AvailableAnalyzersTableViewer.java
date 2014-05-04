package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalyzerInformation;

public class AvailableAnalyzersTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<AnalyzerInformation> languages = new ArrayList<AnalyzerInformation>();

	public AvailableAnalyzersTableViewer(Table table) {
		super(table);
		setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				ProgrammingLanguageAnalyzer programmingLanguage1 = (ProgrammingLanguageAnalyzer) e1;
				ProgrammingLanguageAnalyzer programmingLanguage2 = (ProgrammingLanguageAnalyzer) e2;
				String lang1 = programmingLanguage1.getName()
						+ programmingLanguage1.getVersion();
				String lang2 = programmingLanguage2.getName()
						+ programmingLanguage2.getVersion();
				return lang1.compareTo(lang2);
			}
		});
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
				AnalyzerInformation language = (AnalyzerInformation) element;
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
				AnalyzerInformation language = (AnalyzerInformation) element;
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
			Collection<AnalyzerInformation> collection = (Collection<AnalyzerInformation>) newInput;
			languages.addAll(collection);
		}
		refresh();
	}

	@Override
	public AnalyzerInformation[] getElements(Object inputElement) {
		return languages.toArray(new AnalyzerInformation[languages.size()]);
	}

}
