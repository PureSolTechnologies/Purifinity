/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

public interface Analyser {

    public Language getLanguage();

    public File getFile();

    public ArrayList<CodeRange> getCodeRanges();

    public CodeRangeMetrics getMetrics(CodeRange codeRange);

    public int getLineNumber();

    public Hashtable<Integer, String> getLexerTokens();

    public Hashtable<Integer, String> getParserTokens();
}
