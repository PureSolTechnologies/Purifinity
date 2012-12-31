package com.puresol.coding.analysis;

import com.puresol.coding.analysis.api.RepositoryLocation;
import com.puresol.utils.CodeSearchConfiguration;

public abstract class AbstractRepositoryLocation implements RepositoryLocation {

    private static final long serialVersionUID = -3692883413027085776L;

    private final String name;
    private CodeSearchConfiguration codeSearchConfiguration = new CodeSearchConfiguration();

    public AbstractRepositoryLocation(String name) {
	super();
	this.name = name;
    }

    @Override
    public String getName() {
	return name;
    }

    /**
     * This method returns the set {@link CodeSearchConfiguration}.
     * 
     * @return An object of {@link CodeSearchConfiguration} is returned.
     */
    @Override
    public CodeSearchConfiguration getCodeSearchConfiguration() {
	return codeSearchConfiguration.clone();
    }

    /**
     * This method sets a new {@link CodeSearchConfiguration}.
     * 
     * @param codeSearchConfiguration
     */
    @Override
    public void setCodeSearchConfiguration(
	    CodeSearchConfiguration codeSearchConfiguration) {
	this.codeSearchConfiguration = codeSearchConfiguration.clone();
    }
}
