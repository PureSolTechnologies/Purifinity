/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.AbstractAnalyser;
import com.puresol.coding.lang.Language;
import com.puresol.coding.lang.fortran.metrics.CodeRangeMetrics4Fortran;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranAnalyser extends AbstractAnalyser {

    private static final Logger logger =
	    Logger.getLogger(FortranAnalyser.class);

    private static final String[] FILE_SUFFIXES =
	    { ".f", ".f77", ".f90", ".f95" };

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
     */
    public FortranAnalyser(File projectDirectory, File file) {
	super(projectDirectory, file);
	parse();
    }

    private void parse() {
	try {
	    FortranLexer lexer =
		    new FortranLexer(new FortranPreConditioner(new File(
			    getProjectDirectory().toString() + "/"
				    + getFile().toString()))
			    .getTokenStream());
	    TokenStream tokenStream = lexer.getTokenStream();
	    FortranParser parser = new FortranParser(tokenStream);
	    parser.scan();
	    addCodeRanges(parser.getCodeRanges());
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoMatchingTokenDefinitionFound e) {
	    logger.error(e.getMessage(), e);
	} catch (PartDoesNotMatchException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    protected void calculate() {
	clearAllMetrics();
	for (CodeRange codeRange : getCodeRanges()) {
	    addMetrics(codeRange, new CodeRangeMetrics4Fortran(codeRange));
	}
    }

    public Language getLanguage() {
	return Language.FORTRAN;
    }
}
