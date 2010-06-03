/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.java;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.SourceCodeLexer;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.analysis.AnalyserException;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser extends AbstractAnalyser {

    private static final long serialVersionUID = -3601131473616977648L;

    private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

    public JavaAnalyser(File projectDirectory, File file) {
	super(projectDirectory, file);
    }

    @Override
    public void parse() throws AnalyserException {
	try {
	    SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
		    getProjectDirectory(), getFile());
	    JavaParser parser = (JavaParser) createParserInstance(
		    JavaParser.class, lexer.getTokenStream());
	    parser.scan();
	    setRootCodeRange(parser);
	    return;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoMatchingTokenDefinitionFound e) {
	    logger.error(e.getMessage(), e);
	} catch (PartDoesNotMatchException e) {
	    logger.error(e.getMessage(), e);
	} catch (ParserException e) {
	    logger.error(e.getMessage(), e);
	} catch (LexerException e) {
	    logger.error(e.getMessage(), e);
	}
	throw new AnalyserException(this);
    }

    public ProgrammingLanguage getLanguage() {
	return Java.getInstance();
    }
}
