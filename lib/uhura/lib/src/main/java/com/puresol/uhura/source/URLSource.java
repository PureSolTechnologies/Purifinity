package com.puresol.uhura.source;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLSource extends AbstractSource {

    private static final long serialVersionUID = 7938452623238399125L;

    private final URL url;

    public URLSource(URL url) {
	this.url = url;
    }

    @Override
    public SourceCode load() throws IOException {
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
	URLSource other = (URLSource) obj;
	if (url == null) {
	    if (other.url != null)
		return false;
	} else if (!url.equals(other.url))
	    return false;
	return true;
    }

    @Override
    public Source newRelativeSource(String relativePath) {
	throw new IllegalStateException(
		"This functionality is not implemented, yet!");
    }

}
