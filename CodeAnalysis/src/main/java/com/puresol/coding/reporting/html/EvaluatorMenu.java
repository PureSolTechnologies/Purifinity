package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PathResolutionException;

class EvaluatorMenu {

	public static String getProjectEvaluatorMenuHTML(File htmlRootDirectory,
			String from, ProjectEvaluatorFactory currentEvaluator)
			throws IOException {
		try {
			StringBuilder output = new StringBuilder();
			List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
					.getAll();
			output.append("<ul>\n");
			for (ProjectEvaluatorFactory factory : evaluatorFactories) {
				output.append("<li>");
				output.append("<a href=\"");
				File toPath = HTMLProjectAnalysisCreator.getFile(
						htmlRootDirectory, currentEvaluator);
				output.append(FileUtilities.getRelativePath(from,
						toPath.getPath(), File.separator));
				output.append("\">");
				output.append(factory.getName());
				output.append("</a>");
				if (factory.equals(currentEvaluator)) {
					output.append("<b>");
					output.append(factory.getName());
					output.append("</b>");
				} else {
					output.append(factory.getName());
				}
				output.append("</li>\n");
			}
			output.append("</ul>\n");
			return output.toString();
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}

	public static String getCodeRangeEvaluatorMenuHTML(File htmlRootDirectory,
			String from, AnalyzedFile file, CodeRange codeRange,
			CodeRangeEvaluatorFactory currentEvaluator) throws IOException {
		try {
			StringBuilder output = new StringBuilder();
			List<CodeRangeEvaluatorFactory> evaluatorFactories = CodeRangeEvaluatorManager
					.getAll();
			output.append("<ul>\n");
			for (CodeRangeEvaluatorFactory factory : evaluatorFactories) {
				output.append("<li>");
				output.append("<a href=\"");
				File toPath = HTMLProjectAnalysisCreator.getFile(
						htmlRootDirectory, file, codeRange, factory);
				output.append(FileUtilities.getRelativePath(from,
						toPath.getPath(), File.separator));
				output.append("\">");
				output.append(factory.getName());
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
