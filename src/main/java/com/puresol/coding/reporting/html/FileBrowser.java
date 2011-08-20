package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;

import com.puresol.trees.FileTree;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PathResolutionException;

/**
 * This class provides the generation of HTML code for a simple file browser for
 * HTML projects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
class FileBrowser {

	/**
	 * 
	 * @param htmlRootDirectory
	 *            is the absolute path to the HTML directory where all files are
	 *            create now.
	 * 
	 * @param relativeFile
	 * @param fileTree
	 * @return
	 * @throws IOException
	 */
	public static String getFileBrowserHTML(File htmlRootDirectory,
			File relativeFile, FileTree fileTree) throws IOException {
		return getFileBrowserHTML(htmlRootDirectory, relativeFile, 1, fileTree);
	}

	private static String getFileBrowserHTML(File htmlRootDirectory,
			File relativeFile, int i, FileTree fileTree) throws IOException {
		String[] pathParts = relativeFile.getPath().split(File.separator);
		StringBuilder builder = new StringBuilder();
		builder.append("<ul>\n");
		for (FileTree node : fileTree.getChildren()) {
			builder.append("<li>");
			boolean inPath = ((i < pathParts.length) && (node.getName()
					.equals(pathParts[i])));
			boolean proceed = false;
			if (inPath) {
				builder.append("<b>");
				if (node.hasChildren()) {
					builder.append("-");
					proceed = true;
				}
			} else {
				if (node.hasChildren()) {
					builder.append("+");
				}
			}
			builder.append(createLink(htmlRootDirectory, relativeFile, node));
			if (node.hasChildren()) {
				builder.append(File.separator);
			}
			if (proceed) {
				builder.append(getFileBrowserHTML(htmlRootDirectory,
						relativeFile, i + 1, node));
			}
			if (inPath) {
				builder.append("</b>");
			}
			builder.append("</li>");
		}
		builder.append("</ul>\n");
		return builder.toString();
	}

	private static Object createLink(File htmlRootDirectory, File relativeFile,
			FileTree node) throws IOException {
		try {
			StringBuilder builder = new StringBuilder();
			File codeRangeEvaluatorDirectory = HTMLProjectAnalysisCreator
					.getCodeRangeEvaluatorsDirectory(htmlRootDirectory);

			File current = new File(codeRangeEvaluatorDirectory,
					relativeFile.getPath() + File.separator);
			File target = new File(codeRangeEvaluatorDirectory, node.getPath());
			target = new File(target, "index.html");

			String relativePath = FileUtilities.getRelativePath(FileUtilities
					.normalizePath(current).getPath() + File.separator,
					FileUtilities.normalizePath(target).getPath(), File.separator);
			builder.append("<a href=\"");
			builder.append(relativePath);
			builder.append("\">");
			builder.append(node.getName());
			builder.append("</a>");
			return builder.toString();
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}

}
