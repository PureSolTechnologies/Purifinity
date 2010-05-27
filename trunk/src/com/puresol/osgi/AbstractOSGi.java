package com.puresol.osgi;

import org.osgi.framework.launch.Framework;

public abstract class AbstractOSGi implements OSGi {

    private Framework framework = null;

    @Override
    public final Framework startFramework() throws OSGiException {
	framework = createAndStartFramework();
	return framework;
    }

    @Override
    public final void stopFramework() throws OSGiException {
	stopAndDisposeFramework();
	framework = null;
    }

    @Override
    public boolean isStarted() {
	return (framework != null);
    }

    protected abstract Framework createAndStartFramework() throws OSGiException;

    protected abstract void stopAndDisposeFramework() throws OSGiException;

}
