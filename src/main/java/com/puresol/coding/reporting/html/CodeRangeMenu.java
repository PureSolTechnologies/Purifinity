package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PathResolutionException;

class CodeRangeMenu {

	public static String getCodeRangeMenuHTML(File baseDirectory,
			AnalyzedFile file, Analysis analysis, CodeRange currentCodeRange)
			throws IOException {
		try {
			StringBuilder output = new StringBuilder();
			List<CodeRange> codeRanges = analysis.getAnalyzableCodeRanges();
			output.append("<ul>\n");
			List<String> usedCodeRangeNames = new ArrayList<String>();
			for (CodeRange codeRange : codeRanges) {
				String codeRangeName = codeRange.toString();
				int dummy = 1;
				while (usedCodeRangeNames.contains(codeRangeName)) {
					dummy++;
					codeRangeName = codeRange.toString() + " " + dummy;
				}
				usedCodeRangeNames.add(codeRangeName);
				output.append("<li>");
				output.append("<a href=\"");
				File fromPath = HTMLProjectAnalysisCreator.getDirectory(
						baseDirectory, file);
				File toPath = HTMLProjectAnalysisCreator.getFile(baseDirectory,
						file, codeRangeName);
				output.append(FileUtilities.getRelativePath(fromPath.getPath()
						+ File.separator, toPath.getPath(), File.separator));
				output.append("\">");
				output.append(codeRangeName);
				output.append("</a>");
				output.append("</li>\n");
			}
			output.append("</ul>\n");
			return output.toString();
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}
}
