/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.lang.Language;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CPPAnalyser extends AbstractAnalyser {

    private static final Logger logger =
	    Logger.getLogger(CPPAnalyser.class);

    private static final String[] FILE_SUFFIXES =
	    { ".h", ".c", ".hpp", ".cpp", ".hxx", ".cxx" };

    public static boolean isSuitable(File file) {
	String name = file.getName();
	for (String suffix : FILE_SUFFIXES) {
	    if (name.endsWith(suffix)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * This is the default constructor.
     * 
     * @param A
     *            file to be analysed.
     * @throws LexerException
     */
    public CPPAnalyser(File projectDirectory, File file) {
	super(projectDirectory, file);
	parse();
    }

    private void parse() {
	try {
	    DefaultPreConditioner conditioner =
		    new DefaultPreConditioner(getProjectDirectory(),
			    getFile());
	    TokenStream tokenStream = conditioner.getTokenStream();
	    CPPLexer lexer = new CPPLexer(tokenStream);
	    tokenStream = lexer.getTokenStream();
	    CPPParser parser = new CPPParser(tokenStream);
	    parser.scan();
	    addCodeRanges(parser.getCodeRanges());
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoMatchingTokenDefinitionFound e) {
	    logger.error(e.getMessage(), e);
	} catch (PartDoesNotMatchException e) {
	    logger.error(e.getMessage(), e);
	} catch (LexerException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    public Language getLanguage() {
	return Language.JAVA;
    }
}
