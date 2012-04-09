package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.ObjectUtilities;

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
    private final File file;
    private final int hashcode;
    private final AnalyzedFileInformation information;

    public AnalyzedFile(File sourceDirectory, File file) throws IOException {
	super();
	if (sourceDirectory == null) {
	    throw new IllegalArgumentException("sourceDirectory is null!");
	}
	if (file == null) {
	    throw new IllegalArgumentException("file is null!");
	}
	this.sourceDirectory = sourceDirectory;
	this.file = file;
	hashcode = ObjectUtilities.calculateConstantHashCode(sourceDirectory,
		file);
	information = new AnalyzedFileInformation(FileUtilities.createHashId(
		getSourceFile(), HashAlgorithm.SHA256), file);
    }

    public final File getFile() {
	return file;
    }

    public final File getSourceFile() {
	return new File(sourceDirectory, file.getPath());
    }

    public final AnalyzedFileInformation getInformation() {
	return information;
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
	return true;
    }

    @Override
    public int compareTo(AnalyzedFile other) {
	return this.file.compareTo(other.file);
    }
}
