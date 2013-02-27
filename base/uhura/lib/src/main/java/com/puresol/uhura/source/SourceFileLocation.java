package com.puresol.uhura.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SourceFileLocation extends AbstractCodeLocation {

    private static final long serialVersionUID = -4803348905641081874L;

    private final File repositoryDirectory;
    private final String internalPath;
    private final File file;

    public SourceFileLocation(String repositoryDirectory, String internalPath) {
	this.repositoryDirectory = new File(repositoryDirectory);
	this.internalPath = internalPath;
	file = new File(repositoryDirectory, internalPath);
    }

    public SourceFileLocation(File repositoryDirectory, String internalPath) {
	this.repositoryDirectory = repositoryDirectory;
	this.internalPath = internalPath;
	file = new File(repositoryDirectory, internalPath);
    }

    public SourceFileLocation(File repositoryDirectory, File internalPath) {
	this.repositoryDirectory = repositoryDirectory;
	this.internalPath = internalPath.getPath();
	file = new File(repositoryDirectory, this.internalPath);
    }

    @Override
    public InputStream openStream() throws IOException {
	return new FileInputStream(file);
    }

    @Override
    public SourceCode loadSourceCode() throws IOException {
	FileInputStream stream = new FileInputStream(file);
	try {
	    return SourceCode.read(stream, this);
	} finally {
	    stream.close();
	}
    }

    @Override
    public String getHumanReadableLocationString() {
	return internalPath;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((internalPath == null) ? 0 : internalPath.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SourceFileLocation other = (SourceFileLocation) obj;
	if (internalPath == null) {
	    if (other.internalPath != null)
		return false;
	} else if (!internalPath.equals(other.internalPath))
	    return false;
	return true;
    }

    @Override
    public CodeLocation newRelativeSource(String relativePath) {
	File internalFile = new File(internalPath);
	File internalParentFile = internalFile.getParentFile();
	File newInternalPath;
	if (internalParentFile == null) {
	    newInternalPath = new File(relativePath);
	} else {
	    newInternalPath = new File(internalParentFile.getPath(),
		    relativePath);
	}
	return new SourceFileLocation(repositoryDirectory,
		newInternalPath.getPath());
    }

    @Override
    public String getName() {
	return internalPath;
    }

    @Override
    public String getInternalLocation() {
	return internalPath;
    }

    @Override
    public boolean isAvailable() {
	return file.exists();
    }
}
