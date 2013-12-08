package com.puresoltechnologies.parsers.impl.source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLSourceCodeLocation extends AbstractCodeLocation {

    private static final long serialVersionUID = 7938452623238399125L;

    private final URL url;

    public URLSourceCodeLocation(URL url) {
	this.url = url;
    }

    @Override
    public InputStream openStream() throws IOException {
	return url.openStream();
    }

    @Override
    public SourceCode loadSourceCode() throws IOException {
	InputStream stream = url.openStream();
	try {
	    return SourceCode.read(stream, this);
	} finally {
	    stream.close();
	}
    }

    @Override
    public String getHumanReadableLocationString() {
	return url.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((url == null) ? 0 : url.hashCode());
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
	URLSourceCodeLocation other = (URLSourceCodeLocation) obj;
	if (url == null) {
	    if (other.url != null)
		return false;
	} else if (!url.equals(other.url))
	    return false;
	return true;
    }

    @Override
    public CodeLocation newRelativeSource(String relativePath) {
	throw new IllegalStateException(
		"This functionality is not implemented, yet!");
    }

    @Override
    public String getName() {
	return new File(url.getPath()).getName();
    }

    @Override
    public String getInternalLocation() {
	return new File(url.getPath()).getParent();
    }

    @Override
    public boolean isAvailable() {
	try {
	    InputStream stream = url.openStream();
	    try {
		return stream != null;
	    } finally {
		stream.close();
	    }
	} catch (IOException e) {
	    return false;
	}
    }

}
