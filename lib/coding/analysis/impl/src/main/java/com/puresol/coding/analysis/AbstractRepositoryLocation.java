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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime
		* result
		+ ((codeSearchConfiguration == null) ? 0
			: codeSearchConfiguration.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	AbstractRepositoryLocation other = (AbstractRepositoryLocation) obj;
	if (codeSearchConfiguration == null) {
	    if (other.codeSearchConfiguration != null)
		return false;
	} else if (!codeSearchConfiguration
		.equals(other.codeSearchConfiguration))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

}
