package com.puresol.coding.analysis.api;

import java.io.Serializable;
import java.util.List;

import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.CodeSearchConfiguration;

/**
 * This is an interface for a repository source. The actual implementation may
 * be an access to a SCM system like GIT, Subversion or CVS or it might be a
 * directory which contains the checked out code. The actual implementation is
 * free to get a wide variety of repository sources connected.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface RepositoryLocation extends Serializable {

    /**
     * This method returns the repository's name,
     * 
     * @return A {@link String} is returned containing the name of the
     *         repository.
     */
    public String getName();

    /**
     * This method returns a list of {@link CodeLocation} from all source
     * codes which are available within the specified repository.
     * 
     * @return A {@link List} of {@link CodeLocation} is returned
     *         containing all locations.
     */
    public List<CodeLocation> getSourceCodes();

    /**
     * This method returns the set {@link CodeSearchConfiguration}.
     * 
     * @return An object of {@link CodeSearchConfiguration} is returned.
     */
    public CodeSearchConfiguration getCodeSearchConfiguration();

    /**
     * This method sets a new {@link CodeSearchConfiguration}.
     * 
     * @param codeSearchConfiguration
     */
    public void setCodeSearchConfiguration(
	    CodeSearchConfiguration codeSearchConfiguration);
}
