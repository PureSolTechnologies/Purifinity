package com.puresol.osgi;

import org.osgi.framework.launch.Framework;

public interface OSGi {

    public Framework startFramework() throws OSGiException;

    public void stopFramework() throws OSGiException;
    
    public boolean isStarted();

}
