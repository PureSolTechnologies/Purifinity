package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;

public class FileAnalysisEditorInput implements IEditorInput {

	private final AnalysisRun analysisRun;
	private final AnalysisInformation analyzedCode;

	public FileAnalysisEditorInput(AnalysisRun analysisRun,
			AnalysisInformation analyzedCode) {
		super();
		this.analysisRun = analysisRun;
		this.analyzedCode = analyzedCode;
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
		SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
				analyzedCode.getHashId()).getSourceCodeLocation();
		return sourceCodeLocation.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
				analyzedCode.getHashId()).getSourceCodeLocation();
		return sourceCodeLocation.getHumanReadableLocationString();
	}

	public final AnalysisInformation getAnalysisInformation() {
		return analyzedCode;
	}

	public final AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

}
