package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisServiceClient;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class RepositoryTypeComboViewer extends ComboViewer implements
		IStructuredContentProvider {

	private static final Logger logger = LoggerFactory
			.getLogger(RepositoryTypeComboViewer.class);

	private final Set<RepositoryType> repositoryTypes = new LinkedHashSet<>();

	public RepositoryTypeComboViewer(CCombo list) {
		super(list);
		init();
	}

	public RepositoryTypeComboViewer(Combo list) {
		super(list);
		init();
	}

	public RepositoryTypeComboViewer(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public RepositoryTypeComboViewer(Composite parent) {
		super(parent);
		init();
	}

	private void init() {
		setContentProvider(ArrayContentProvider.getInstance());
		setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				RepositoryType repositoryType = (RepositoryType) element;
				return repositoryType.getName() + " ("
						+ repositoryType.getDescription() + ")";
			}
		});
	}

	@Override
	public void refresh() {
		super.refresh();
		try {
			setInput(AnalysisServiceClient.getInstance().getRepositoryTypes());
		} catch (IOException e) {
			setInput(new LinkedHashSet<RepositoryType>());
			logger.error("Could not read the available repository types.", e);
		}
	}

	public RepositoryType getSelectedRepositoryType() {
		IStructuredSelection selection = (IStructuredSelection) getSelection();
		return (RepositoryType) selection.getFirstElement();
	}

	public void setSelection(RepositoryType repositoryTypeSelection) {
		if (repositoryTypeSelection != null) {
			setSelection(new StructuredSelection(repositoryTypeSelection));
		}
	}

	@Override
	public void dispose() {
		// intentionally left blank
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		repositoryTypes.clear();
		if (newInput != null) {
			if (Collection.class.isAssignableFrom(newInput.getClass())) {
				@SuppressWarnings("unchecked")
				Collection<RepositoryType> repositoryTypes = (Collection<RepositoryType>) newInput;
				this.repositoryTypes.addAll(repositoryTypes);
			}
		}
		refresh();
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return repositoryTypes.toArray();
	}

}
