package com.puresol.coding.lang.c11.preprocessor;

import java.util.ArrayList;

import com.puresol.coding.lang.c11.C11;
import com.puresol.coding.lang.c11.preprocessor.internal.DefinedMacros;
import com.puresol.coding.lang.c11.preprocessor.internal.TreeMacroProcessor;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.preprocessor.Preprocessor;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.SourceCode;

/**
 * This is a C preprocessor on basis on C11 grammar.
 * 
 * @author Rick-Rainer Ludwig
 */
public class C11Preprocessor implements Preprocessor {

    private static final String PREPROSSING_FILE_PRODUCTION_NAME = "preprocessing-file";
    private static final String LINE_TERMINATOR_TOKEN_NAME = "LineTerminator";

    /**
     * This is the default nesting limit as defined by the environmental
     * condition specification in the C language specification.
     */
    private static final int DEFAULT_NESTING_LIMIT = 63;

    private static final Grammar preprocessorGrammar = preparePreProcessorGrammar();

    /**
     * This method prepares the preprocessor grammar. Since the preprocessor
     * grammar is explained in the C language specification and the grammar is
     * incorporated into the C grammar, we use the C grammar as basis,
     * 
     * The differences are:
     * 
     * 1) We use the 'preprocessing-file' production as start production for
     * parsing.
     * 
     * 2) We set the 'LineTerminator' token to visible due to the need to use it
     * for parsing to recognize the end of a preprocessor statement.
     * 
     * @return A {@link Grammar} is returned suitable for use as C preprocessor
     *         grammar.
     * @throws RuntimeException
     *             is thrown if there is anything wrong with the grammar which
     *             is not to be expected. This should be assured with testing!
     */
    private static Grammar preparePreProcessorGrammar() {
	try {
	    Grammar preprocessorGrammar = createPreprocessorGrammar();
	    setLineTerminatorToVisible(preprocessorGrammar);
	    return preprocessorGrammar;
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize preprocessor grammar!", e);
	}
    }

    /**
     * This method creates a new preprocessor grammar on basis of C11 grammar by
     * setting the start production to
     * {@value #PREPROSSING_FILE_PRODUCTION_NAME}.
     * 
     * @return A {@link Grammar} object is returned with a newly set start
     *         production for pre-processing.
     * @throws GrammarException
     *             is thrown if there is anything wrong with the grammar.
     */
    private static Grammar createPreprocessorGrammar() throws GrammarException {
	Grammar preprocessorGrammar = C11.getInstance().getGrammar()
		.createWithNewStartProduction(PREPROSSING_FILE_PRODUCTION_NAME);
	return preprocessorGrammar;
    }

    /**
     * This method sets the visibility of the line terminator token
     * {@value #LINE_TERMINATOR_TOKEN_NAME} to visible to have it present for
     * preprocessor statement detection.
     * 
     * @param preprocessorGrammar
     *            is the predefined preprocessor grammar used for modification.
     * @throws GrammarException
     *             is throws if anything goes wrong with the grammar.
     */
    private static void setLineTerminatorToVisible(Grammar preprocessorGrammar)
	    throws GrammarException {
	TokenDefinitionSet tokenDefinitions = preprocessorGrammar
		.getTokenDefinitions();
	TokenDefinition lineTerminator = tokenDefinitions
		.getDefinition(LINE_TERMINATOR_TOKEN_NAME);
	ArrayList<TokenDefinition> definitions = (ArrayList<TokenDefinition>) tokenDefinitions
		.getDefinitions();
	definitions.remove(lineTerminator);
	lineTerminator = new TokenDefinition(lineTerminator.getName(),
		lineTerminator.getText(), Visibility.VISIBLE,
		lineTerminator.isIgnoreCase());
	definitions.add(lineTerminator);
    }

    /**
     * This is the set maximum supported nesting. The default value is
     * {@value #DEFAULT_NESTING_LIMIT}.
     */
    private static int nestingLimit = DEFAULT_NESTING_LIMIT;

    /**
     * This is the setter for the {@link #nestingLimit} which controls the
     * maximum #include nesting depth.
     * 
     * @param nestingLimit
     *            is the number of allowed nested #include directives.
     */
    public static void setNestingLimit(int nestingLimit) {
	C11Preprocessor.nestingLimit = nestingLimit;
    }

    /**
     * This is the getter for the {@link #nestingLimit} which controls the
     * maximum #include nesting depth.
     * 
     * @return The number of allowed nested #include directives is returned.
     */
    public static int getNestingLimit() {
	return nestingLimit;
    }

    private final IncludeDirectories includeDirectories;
    private final DefinedMacros definedMacros;
    private final int nestingDepth;

    /**
     * This is the default constructor which is mainly used for testing. The
     * {@link #includeDirectories} are empty, the {@link #definedMacros} are set
     * to system defaults and the {@link #nestingDepth} is 0.
     */
    protected C11Preprocessor() {
	this(new IncludeDirectories(), new DefinedMacros(), 0);
    }

    /**
     * This is the constructor which should be used mainly by external code. The
     * {@link #includeDirectories} are set by the parameter as it is done during
     * compiling time, the {@link #definedMacros} are set by the caller as it is
     * also done by the build system and the {@link #nestingDepth} is set to 0
     * as starting condition.
     */
    public C11Preprocessor(IncludeDirectories includeDirectories,
	    DefinedMacros definedMacros) {
	this(includeDirectories, definedMacros, 0);
    }

    public C11Preprocessor(IncludeDirectories includeDirectories,
	    DefinedMacros definedMacros, int nestingDepth) {
	this.nestingDepth = nestingDepth;
	this.includeDirectories = includeDirectories;
	this.definedMacros = definedMacros;
    }

    @Override
    public SourceCode process(SourceCode sourceCode)
	    throws PreprocessorException {
	try {
	    resetDefinedMacros();
	    boolean added = sourceCode.assureLineTerminatorAtLastLine();
	    ParserTree ast = parseSourceCode(sourceCode);
	    SourceCode preProcessedSourceCode = process(ast);
	    if (added) {
		sourceCode.removeLineTerminatorAtLastLine();
	    }
	    return preProcessedSourceCode;
	} catch (ParserException e) {
	    throw new PreprocessorException(
		    "Could not preprocess source code!", e);
	}
    }

    private ParserTree parseSourceCode(SourceCode sourceCode)
	    throws ParserException {
	PackratParser parser = new PackratParser(preprocessorGrammar);
	return parser.parse(sourceCode);
    }

    private SourceCode process(ParserTree ast) {
	TreeMacroProcessor processor = new TreeMacroProcessor(ast,
		includeDirectories, definedMacros, nestingDepth);
	processor.process();
	return processor.getSourceCode();
    }

    private void resetDefinedMacros() {
	definedMacros.reset();
    }
}
