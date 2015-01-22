package com.puresoltechnologies.purifinity.server.ui.projects.analysis;

import java.util.List;

import com.puresoltechnologies.commons.misc.io.FileUtilities;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;

public class AnalysisContentTreeNodeObject {
	private final AnalysisFileTree analysisFileTree;
	private final String type;

	public AnalysisContentTreeNodeObject(AnalysisFileTree analysisFileTree) {
		super();
		this.analysisFileTree = analysisFileTree;
		if (analysisFileTree.isFile()) {
			type = "file";
		} else {
			type = "directory";
		}
	}

	public AnalysisFileTree getAnalysisFileTree() {
		return analysisFileTree;
	}

	public String getName() {
		return analysisFileTree.getName();
	}

	public String getType() {
		return type;
	}

	public String getSize() {
		return FileUtilities.createHumanReadableSizeString(analysisFileTree
				.getSize());
	}

	public String getSizeRecursive() {
		return FileUtilities.createHumanReadableSizeString(analysisFileTree
				.getSizeRecursive());
	}

	public int getAnalysisCount() {
		return analysisFileTree.getAnalyzedCodes().size();
	}

	public String getAnalysisLanguages() {
		StringBuffer stringBuffer = new StringBuffer();
		List<AnalysisInformation> analyzedCodes = analysisFileTree
				.getAnalyzedCodes();
		for (AnalysisInformation analysisInformation : analyzedCodes) {
			if (stringBuffer.length() > 0) {
				stringBuffer.append('\n');
			}
			stringBuffer.append(analysisInformation.getLanguageName());
			stringBuffer.append(' ');
			stringBuffer.append(analysisInformation.getLanguageVersion()
					.toString());
			stringBuffer.append(" (");
			stringBuffer.append(analysisInformation.getDuration());
			stringBuffer.append("ms)");
		}
		return stringBuffer.toString();
	}

	public String getSourceLocation() {
		SourceCodeLocation sourceCodeLocation = analysisFileTree
				.getSourceCodeLocation();
		if (sourceCodeLocation != null) {
			return sourceCodeLocation.getHumanReadableLocationString();
		} else {
			return "";
		}
	}
}
