package com.puresol.coding.analysis;

import java.io.File;
import java.io.Serializable;

import com.puresol.coding.CodeRange;
import com.puresol.coding.evaluator.Evaluator;

/**
 * This class is for keeping a list of analyzed files within ProjectAnalyzer.
 * This class provides easy and standardized access to the workspace
 * directories.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzedFile implements Comparable<AnalyzedFile>, Serializable {

	private static final long serialVersionUID = 2030120585873480183L;

	private final File sourceDirectory;
	private final File workspaceDirectory;
	private final File file;
	private final int hashcode;

	public AnalyzedFile(File sourceDirectory, File workspaceDirectory, File file) {
		super();
		if (sourceDirectory == null) {
			throw new IllegalArgumentException("sourceDirectory is null!");
		}
		if (workspaceDirectory == null) {
			throw new IllegalArgumentException("workspaceDirectory is null!");
		}
		if (file == null) {
			throw new IllegalArgumentException("file is null!");
		}
		this.sourceDirectory = sourceDirectory;
		this.workspaceDirectory = workspaceDirectory;
		this.file = file;
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime
				* result
				+ ((workspaceDirectory == null) ? 0 : workspaceDirectory
						.hashCode());
		hashcode = result;
	}

	public File getSourceDirectory() {
		return sourceDirectory;
	}

	public File getSourceFile() {
		return new File(sourceDirectory, file.getPath());
	}

	public File getWorkspaceDirectory() {
		return workspaceDirectory;
	}

	public File getFile() {
		return file;
	}

	public File getFileDirectory() {
		return new File(workspaceDirectory, file.getPath());
	}

	public File getAnalyzerFile() {
		return new File(getFileDirectory(), "analyzer.persist");
	}

	public File getReportFile(Evaluator evaluator, CodeRange codeRange) {
		return new File(getFileDirectory(), evaluator.getName() + "-"
				+ codeRange.getType().getName() + "_" + codeRange.getName()
				+ ".report");
	}

	public File getResultsFile(Evaluator evaluator, CodeRange codeRange) {
		return new File(getFileDirectory(), evaluator.getName() + "-"
				+ codeRange.getType().getName() + "_" + codeRange.getName()
				+ ".results");
	}

	public File getParserTreeFile() {
		return new File(getFileDirectory(), "parser_tree.persist");
	}

	public File getPropertyFile() {
		return new File(getFileDirectory(), "analysis.properties");
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalyzedFile other = (AnalyzedFile) obj;
		if (this.hashcode != other.hashcode) {
			return false;
		}
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (workspaceDirectory == null) {
			if (other.workspaceDirectory != null)
				return false;
		} else if (!workspaceDirectory.equals(other.workspaceDirectory))
			return false;
		return true;
	}

	@Override
	public int compareTo(AnalyzedFile other) {
		return this.file.compareTo(other.file);
	}
}
