package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;

public class DirectoryAnalysisEditorInput implements IEditorInput {

	private final AnalysisFileTree directory;
	private final AnalysisRun analysisRun;

	public DirectoryAnalysisEditorInput(AnalysisFileTree directory,
			AnalysisRun analysisRun) {
		super();
		this.directory = directory;
		this.analysisRun = analysisRun;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return directory.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return directory.getPathFile(false).getPath();
	}

	public final AnalysisFileTree getDirectory() {
		return directory;
	}

	public final AnalysisRun getAnalysisRun() {
		return analysisRun;
	}
}
