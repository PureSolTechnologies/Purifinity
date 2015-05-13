package com.puresoltechnologies.purifinity.server.core.api.analysis;

import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

/**
 * This is a common interface for analyzer service manager local and remote.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalyzerServiceManagerCommon extends
		ServiceManager<AnalyzerServiceInformation, ProgrammingLanguageAnalyzer> {

	/**
	 * This method looks for the analyzer for a given language.
	 * 
	 * @param languageName
	 *            is the name of the language.
	 * @param languageVersion
	 *            is the version of the language.
	 * @return A {@link AnalyzerServiceInformation} object is returned
	 *         containing the relevant information.
	 */
	public AnalyzerServiceInformation findByName(String languageName,
			String languageVersion);

}
