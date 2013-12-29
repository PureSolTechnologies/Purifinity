package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;

public class FileAnalysisEditorInput implements IEditorInput {

	private final AnalyzedCode analyzedCode;
	private final AnalysisRun analysisRun;

	public FileAnalysisEditorInput(AnalyzedCode analyzedCode,
			AnalysisRun analysisRun) {
		super();
		this.analyzedCode = analyzedCode;
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
		return analyzedCode.getSourceLocation().getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return analyzedCode.getSourceLocation()
				.getHumanReadableLocationString();
	}

	public final AnalyzedCode getAnalyzedCode() {
		return analyzedCode;
	}

	public final AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

}
