package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;

public interface Analyser {

	public Language getLanguage();

	public File getFile();

	public ArrayList<CodeRange> getCodeRanges();

}
