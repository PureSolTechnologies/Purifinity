package com.puresoltechnologies.purifinity.analysis.spi;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;

/**
 * This class is an abstract implementation of
 * {@link ProgrammingLanguageAnalyzer}.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractProgrammingLanguageAnalyzer extends
		AbstractProgrammingLanguage implements ProgrammingLanguageAnalyzer {

	protected static String patternsToString(String[] patternsArray) {
		StringBuilder patternsString = new StringBuilder();
		for (String pattern : patternsArray) {
			if (patternsString.length() > 0) {
				patternsString.append('\n');
			}
			patternsString.append(pattern);
		}
		return patternsString.toString();
	}

	protected static String[] stringToPatterns(String patternsString) {
		List<String> patternsList = Arrays.asList(patternsString.split("\n"));
		Iterator<String> iterator = patternsList.iterator();
		while (iterator.hasNext()) {
			String pattern = iterator.next();
			if (pattern.isEmpty()) {
				iterator.remove();
			}
		}
		return patternsList.toArray(new String[patternsList.size()]);
	}

	protected AbstractProgrammingLanguageAnalyzer(String name, String version) {
		super(name, version);
	}

	@Override
	public CodeAnalysis analyze(SourceCodeLocation sourceCodeLocation)
			throws AnalyzerException, IOException {
		CodeAnalyzer analyser = createAnalyser(sourceCodeLocation);
		analyser.analyze();
		return analyser.getAnalysis();
	}
}
