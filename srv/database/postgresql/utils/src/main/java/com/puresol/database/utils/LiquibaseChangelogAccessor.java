package com.puresol.database.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import liquibase.resource.ResourceAccessor;

public class LiquibaseChangelogAccessor implements ResourceAccessor {

    public static final String LIQUIBASE_CHANGELOG_RESOURCE = "/liquibase/changelog.xml";

    @Override
    public ClassLoader toClassLoader() {
	return LiquibaseChangelogAccessor.class.getClassLoader();
    }

    @Override
    public Enumeration<URL> getResources(String packageName) throws IOException {
	return LiquibaseChangelogAccessor.class.getClassLoader().getResources(
		packageName);
    }

    @Override
    public InputStream getResourceAsStream(String file) throws IOException {
	return LiquibaseChangelogAccessor.class.getResourceAsStream(file);
    }

}
