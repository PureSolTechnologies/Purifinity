package com.puresol.purifinity.coding.lang.c11.preprocessor;

import java.util.List;
import java.util.regex.Pattern;

import com.puresol.commons.trees.TreeException;
import com.puresol.commons.utils.io.LineTerminator;
import com.puresol.purifinity.coding.lang.c11.preprocessor.internal.C11PreprocessorParser;
import com.puresol.purifinity.coding.lang.c11.preprocessor.internal.TreeMacroProcessor;
import com.puresol.purifinity.uhura.parser.ParserException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.preprocessor.Preprocessor;
import com.puresol.purifinity.uhura.preprocessor.PreprocessorException;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.uhura.source.SourceCodeLine;

/**
 * This is a C preprocessor on basis on C11 grammar.
 * 
 * Due to its changing internal state during run is this implementation not
 * thread-safe! Additionally, an instance of this object can only be called once
 * meaningfully due to a change to {@link #definedMacros}!
 * 
 * @author Rick-Rainer Ludwig
 */
public class C11Preprocessor implements Preprocessor {

	/**
	 * This is the default nesting limit as defined by the environmental
	 * condition specification in the C language specification.
	 */
	private static final int DEFAULT_NESTING_LIMIT = 63;

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
	/**
	 * This field contains the currently defined macros. <b>Attention: This
	 * parameter is altered during a processor run by adding the newly defined
	 * macros and removing the undefined.</b>
	 */
	private final DefinedMacros definedMacros;
	/**
	 * This field contains the current nesting depth.
	 */
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
	 * 
	 * @param includeDirectories
	 *            is the {@link IncludeDirectories} object which contains the
	 *            directories for the #include statement.
	 * @param definedMacros
	 *            is the {@link DefinedMacros} object which contains the
	 *            predefined macros. <b>Attention: This parameter is altered
	 *            during a processor run by adding the newly defined macros and
	 *            removing the undefined.</b>
	 */
	public C11Preprocessor(IncludeDirectories includeDirectories,
			DefinedMacros definedMacros) {
		this(includeDirectories, definedMacros, 0);
	}

	/**
	 * This constructor is mainly used internally. The nestingDepth is set to
	 * track the current nesting depth to avoid recursive infinite loops.
	 * 
	 * @param includeDirectories
	 *            is the {@link IncludeDirectories} object which contains the
	 *            directories for the #include statement.
	 * @param definedMacros
	 *            is the {@link DefinedMacros} object which contains the
	 *            predefined macros. <b>Attention: This parameter is altered
	 *            during a processor run by adding the newly defined macros and
	 *            removing the undefined.</b>
	 * @param nestingDepth
	 *            is the current nesting depth.
	 */
	public C11Preprocessor(IncludeDirectories includeDirectories,
			DefinedMacros definedMacros, int nestingDepth) {
		this.nestingDepth = nestingDepth;
		this.includeDirectories = includeDirectories;
		this.definedMacros = definedMacros;
	}

	/**
	 * <pre>
	 * This method overrides {@link Preprocessor#process(SourceCode)}.
	 * 
	 * Here we have three things to do:
	 * <ol>
     * <li>We assure the presence of a line terminator in the last line of the source code.</li>
     * <li>The processing of the source code with a {@link TreeMacroProcessor} is started.</li>
     * <li>If a line terminator was added before, we remove the line terminator again to reset to the original code.</li>
     * </ol>
	 */
	@Override
	public SourceCode process(SourceCode sourceCode)
			throws PreprocessorException {
		boolean added = assureLineTerminatorAtLastLine(sourceCode);
		SourceCode preProcessedSourceCode = performPreprocessing(sourceCode);
		if (added) {
			preProcessedSourceCode.removeLineTerminatorAtLastLine();
		}
		return preProcessedSourceCode;
	}

	/**
	 * This method checks the last line of code for the presence of a line
	 * terminator. If no line terminator is found, the last line is replaced by
	 * a copy with an added Unix-LineTerminator. Otherwise, the this object is
	 * not altered.
	 * 
	 * This functionality is used for source codes where the last line has to
	 * have a line terminator for parsing, e.g. for the C preprocessor.
	 * 
	 * @return True is returned in case a line terminator was added. This return
	 *         value is used to control the call to
	 *         {@link #removeLineTerminatorAtLastLine()}.
	 */
	public boolean assureLineTerminatorAtLastLine(SourceCode sourceCode) {
		List<SourceCodeLine> lines = sourceCode.getLines();
		if (lines.size() == 0) {
			throw new IllegalStateException(
					"The source code must have at least on line of code!");
		}
		SourceCodeLine lastLine = lines.get(lines.size() - 1);
		String text = lastLine.getLine();
		for (LineTerminator terminator : LineTerminator.values()) {
			if (text.endsWith(terminator.getCRString())) {
				return false;
			}
		}
		lines.remove(lastLine);
		lastLine = new SourceCodeLine(lastLine.getSource(),
				lastLine.getLineNumber(), text + "\n");
		lines.add(lastLine);
		/*
		 * We only need to remove the added terminator if the effected line is
		 * not a preprocessor line!
		 */
		boolean removeAddedTerminator = !Pattern.matches("^\\s*#.*", text);
		return removeAddedTerminator;
	}

	/**
	 * This method performs the actual processing by using a
	 * {@link TreeMacroProcessor} object.
	 * 
	 * @param sourceCode
	 * @return
	 * @throws PreprocessorException
	 */
	private SourceCode performPreprocessing(SourceCode sourceCode)
			throws PreprocessorException {
		try {
			C11PreprocessorParser parser = new C11PreprocessorParser();
			ParserTree ast = parser.parse(sourceCode);
			TreeMacroProcessor processor = new TreeMacroProcessor(ast,
					includeDirectories, definedMacros, nestingDepth);
			processor.process();
			return processor.getSourceCode();
		} catch (ParserException e) {
			throw new PreprocessorException(
					"Could not preprocess source code!", e);
		} catch (TreeException e) {
			throw new PreprocessorException(
					"Could not preprocess source code!", e);
		}
	}
}
