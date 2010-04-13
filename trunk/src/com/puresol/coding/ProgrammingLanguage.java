package com.puresol.coding;

import java.io.File;

import com.puresol.coding.analysis.Analyser;
import com.puresol.utils.ClassInstantiationException;

public interface ProgrammingLanguage {

	public String getName();

	public boolean isObjectOriented();

	public boolean isSuitable(File file);

	public Analyser createAnalyser(File directory, File file)
			throws ClassInstantiationException;

}
