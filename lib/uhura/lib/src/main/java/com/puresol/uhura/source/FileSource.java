package com.puresol.uhura.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileSource extends AbstractSource {

    private static final long serialVersionUID = -4803348905641081874L;

    private final File file;

    public FileSource(File file) {
	this.file = file;
    }

    @Override
    public SourceCode load() throws IOException {
	FileInputStream stream = new FileInputStream(file);
	try {
	    return SourceCode.read(stream, this);
	} finally {
	    stream.close();
	}
    }

    @Override
    public String getHumanReadableLocationString() {
	return file.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((file == null) ? 0 : file.hashCode());
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
	FileSource other = (FileSource) obj;
	if (file == null) {
	    if (other.file != null)
		return false;
	} else if (!file.equals(other.file))
	    return false;
	return true;
    }

    @Override
    public Source newRelativeSource(String relativePath) {
	return new FileSource(new File(file.getParentFile(), relativePath));
    }
}
