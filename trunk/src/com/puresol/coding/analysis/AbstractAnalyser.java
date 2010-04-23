/***************************************************************************
 *
 *   AbstractAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.TokenStream;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.di.DIClassBuilder;
import com.puresol.utils.di.Injection;

abstract public class AbstractAnalyser implements Analyser {

    private static final long serialVersionUID = -2593701440766091118L;

    private static final Logger logger = Logger
	    .getLogger(AbstractAnalyser.class);

    private final File projectDirectory;
    private final File file;

    private final List<CodeRange> codeRanges = new ArrayList<CodeRange>();
    private final SymbolTable symbols = new SymbolTable();

    public AbstractAnalyser(File projectDirectory, File file) {
	this.projectDirectory = projectDirectory;
	this.file = file;
    }

    public File getProjectDirectory() {
	return projectDirectory;
    }

    @Override
    public File getFile() {
	return file;
    }

    protected void addCodeRanges(List<CodeRange> codeRanges) {
	this.codeRanges.addAll(codeRanges);
    }

    @Override
    public List<CodeRange> getCodeRanges() {
	return codeRanges;
    }

    protected void addSymbol(Symbol symbol) {
	symbols.add(symbol);
    }

    @Override
    public SymbolTable getSymbols() {
	return symbols;
    }

    protected Parser createParserInstance(Class<? extends Parser> clazz,
	    TokenStream tokenStream) throws ParserException {
	try {
	    return DIClassBuilder.forInjections(
		    Injection.named("StartPosition", Integer.valueOf(0)),
		    Injection.named("TokenStream", tokenStream),
		    Injection.named("SymbolTable", symbols)).createInstance(
		    clazz);
	} catch (ClassInstantiationException e) {
	    logger.error(e.getMessage(), e);
	    throw new ParserException(e.getMessage());
	}
    }
}
