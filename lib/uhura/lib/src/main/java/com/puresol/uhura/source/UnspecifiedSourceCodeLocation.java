package com.puresol.uhura.source;

import java.io.IOException;

public class UnspecifiedSourceCodeLocation extends AbstractCodeLocation {

    private static final long serialVersionUID = 5446070531559019716L;

    @Override
    public String getHumanReadableLocationString() {
	return "unspecified source";
    }

    @Override
    public SourceCode load() throws IOException {
	throw new IOException(
		"This is an unspecified source. There is not source information available where the source can be loaded from.");
    }

    @Override
    public boolean equals(Object obj) {
	return this == obj;
    }

    @Override
    public int hashCode() {
	return 42;
    }

    @Override
    public CodeLocation newRelativeSource(String relativePath) {
	throw new IllegalStateException(
		"Cannot provide a new relative source to an unspecified source!");
    }

    @Override
    public String getName() {
	return "unspecified source";
    }

    @Override
    public String getInternalLocation() {
	return "";
    }
}
