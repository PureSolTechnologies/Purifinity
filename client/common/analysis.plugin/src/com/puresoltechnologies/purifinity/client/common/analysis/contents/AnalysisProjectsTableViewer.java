package com.puresoltechnologies.purifinity.client.common.analysis.contents;

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

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.analysis.impl.RepositoryLocationCreator;

public class AnalysisProjectsTableViewer extends TableViewer implements
		IStructuredContentProvider {

	private final List<AnalysisProjectSettings> analysisProjectsSettings = new ArrayList<AnalysisProjectSettings>();

	public AnalysisProjectsTableViewer(Table table) {
		super(table);
		setContentProvider(this);
		setupNameColumn();
		setupDescriptionColumn();
		setupRepositoryColumn();
		setupCreatedColumn();
	}

	private void setupNameColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalysisProjectSettings analysisProject = (AnalysisProjectSettings) element;
				return analysisProject.getName();
			}
		});
	}

	private void setupDescriptionColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Description");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalysisProjectSettings analysisProject = (AnalysisProjectSettings) element;
				return analysisProject.getDescription();
			}
		});
	}

	private void setupRepositoryColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Repository");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				AnalysisProjectSettings analysisProject = (AnalysisProjectSettings) element;
				return RepositoryLocationCreator.createFromSerialization(
						analysisProject.getRepositoryLocation())
						.getHumanReadableLocationString();
			}
		});
	}

	private void setupCreatedColumn() {
		TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText("Created");
		nameColumn.getColumn().setWidth(100);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return "UNKNOWN!!!";
				// AnalysisProjectSettings analysisProject =
				// (AnalysisProjectSettings) element;
				// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				// "yyyy-mm-dd HH:MM:ss");
				// return
				// simpleDateFormat.format(analysisProject.getInformation()
				// .getCreationTime());
			}
		});
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		analysisProjectsSettings.clear();
		if (newInput == null) {
			return;
		}
		if (Collection.class.isAssignableFrom(newInput.getClass())) {
			@SuppressWarnings("unchecked")
			Collection<AnalysisProjectSettings> collection = (Collection<AnalysisProjectSettings>) newInput;
			analysisProjectsSettings.addAll(collection);
		}
		refresh();
	}

	@Override
	public AnalysisProject[] getElements(Object inputElement) {
		return analysisProjectsSettings
				.toArray(new AnalysisProject[analysisProjectsSettings.size()]);
	}

}
