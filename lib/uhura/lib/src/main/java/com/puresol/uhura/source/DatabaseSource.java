package com.puresol.uhura.source;

import java.io.IOException;

public class DatabaseSource implements CodeLocation {

    private static final long serialVersionUID = 5922314657741968891L;

    @Override
    public String getHumanReadableLocationString() {
	return "database:";
    }

    @Override
    public SourceCode load() throws IOException {
	throw new IOException("This source is not implemented, yet!");
    }

    @Override
    public CodeLocation newRelativeSource(String relativePath) {
	throw new IllegalStateException("This source is not implemented, yet!");
    }

    @Override
    public String getName() {
	return "database";
    }

    @Override
    public String getLocation() {
	return "";
    }
}
