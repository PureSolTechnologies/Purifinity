package com.puresoltechnologies.purifinity.coding.analysis.api;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import com.puresoltechnologies.commons.utils.FileSearchConfiguration;
import com.puresoltechnologies.parsers.impl.source.CodeLocation;

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
	 * This method generates a human readable string where the source comes
	 * from. For {@link File} and {@link URL}, the normal toString()
	 * implementations might be sufficient. For {@link DatabaseCodeLocation} for
	 * example, the string might be something else. This String is generated
	 * here.
	 * 
	 * @return A {@link String} is returned telling the original location in
	 *         human readable way.
	 */
	public String getHumanReadableLocationString();

	/**
	 * This method returns a list of {@link CodeLocation} from all source codes
	 * which are available within the specified repository.
	 * 
	 * @return A {@link List} of {@link CodeLocation} is returned containing all
	 *         locations.
	 */
	public List<CodeLocation> getSourceCodes();

	/**
	 * This method returns the set {@link FileSearchConfiguration}.
	 * 
	 * @return An object of {@link FileSearchConfiguration} is returned.
	 */
	public FileSearchConfiguration getCodeSearchConfiguration();

	/**
	 * This method sets a new {@link FileSearchConfiguration}.
	 * 
	 * @param codeSearchConfiguration
	 */
	public void setCodeSearchConfiguration(
			FileSearchConfiguration codeSearchConfiguration);
}
