package com.puresol.coding.lang.c11.preprocessor.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.puresol.coding.lang.c11.grammar.C11Grammar;
import com.puresol.trees.TreeException;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

public class C11PreprocessorParser {

    private static final String LINE_TERMINATOR_TOKEN_NAME = "LineTerminator";

    private static final Pattern DIRECTIVE_LINE_START = Pattern
	    .compile("^\\s*#");
    private static final Pattern IF_SECTION_START = Pattern
	    .compile("^\\s*#\\s*(if|ifdef|ifndef)");
    private static final Pattern CONTROL_LINE_START = Pattern
	    .compile("^\\s*#\\s*(include|define|undef|line|error|pragma|\\n)");
    private static final Pattern NON_DIRECTIVE_LINE_START = Pattern
	    .compile("^\\s*#\\s*(^(include|define|undef|line|error|pragma|if|ifdef|ifndef|elif|else|endif|\\n))");

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
    public static void setLineTerminatorToVisible(Grammar preprocessorGrammar)
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

    private final Grammar grammar = readGrammar();
    /**
     * This field actually contains the group production, but it is only used
     * within groups of pure text-lines. This is done to catch also comment
     * blocks.
     */
    private final PackratParser textGroupParser;
    private final PackratParser controlLineParser;
    private final PackratParser nonDirectiveLineParser;

    private final PackratParser ifGroup;
    private final PackratParser elifGroupParser;
    private final PackratParser elseGroupParser;
    private final PackratParser endifLineParser;

    public C11PreprocessorParser() {
	super();
	try {
	    textGroupParser = new PackratParser(
		    grammar.createWithNewStartProduction("group"));
	    controlLineParser = new PackratParser(
		    grammar.createWithNewStartProduction("control-line"));
	    nonDirectiveLineParser = new PackratParser(
		    grammar.createWithNewStartProduction("non-directive-line"));
	    ifGroup = new PackratParser(
		    grammar.createWithNewStartProduction("if-group"));
	    elifGroupParser = new PackratParser(
		    grammar.createWithNewStartProduction("elif-group"));
	    elseGroupParser = new PackratParser(
		    grammar.createWithNewStartProduction("else-group"));
	    endifLineParser = new PackratParser(
		    grammar.createWithNewStartProduction("endif-line"));
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not create line parsers for preprocessing.", e);
	}
    }

    private Grammar readGrammar() {
	try {
	    Grammar grammar = null;
	    InputStream stream = C11Grammar.class
		    .getResourceAsStream(C11Grammar.GRAMMAR_RESOURCE);
	    try {
		GrammarReader reader = new GrammarReader(stream);
		try {
		    grammar = reader.getGrammar();
		    setLineTerminatorToVisible(grammar);
		} finally {
		    reader.close();
		}
	    } finally {
		stream.close();
	    }
	    return grammar;
	} catch (IOException e) {
	    throw new RuntimeException("Could not load C11 grammar!", e);
	} catch (GrammarException e) {
	    throw new RuntimeException("Could not load C11 grammar!", e);
	}
    }

    public ParserTree parse(SourceCode soureCode) throws TreeException,
	    ParserException {
	ParserTree tree = new ParserTree("preprocessing-file");
	parse(tree, soureCode, 0);
	return tree;
    }

    private int parse(ParserTree tree, SourceCode sourceCode, int currentLine)
	    throws TreeException, ParserException {
	List<SourceCodeLine> lines = sourceCode.getLines();
	int lineCount = 0;
	for (int lineNum = currentLine; lineNum < lines.size(); lineNum++) {
	    SourceCodeLine line = lines.get(lineNum);
	    String text = line.getLine();
	    int count = 0;
	    if (DIRECTIVE_LINE_START.matcher(text).find()) {
		if (IF_SECTION_START.matcher(text).find()) {
		    count = parseIfSection(tree, sourceCode, lineNum);
		} else if (CONTROL_LINE_START.matcher(text).find()) {
		    count = parseContolLine(tree, sourceCode, lineNum);
		} else if (NON_DIRECTIVE_LINE_START.matcher(text).find()) {
		    count = parseNonDirectiveLine(tree, sourceCode, lineNum);
		}
	    } else {
		count = parseTextLine(tree, sourceCode, lineNum);
	    }
	    if (count == 0) {
		break;
	    }
	    lineCount += count;
	    lineNum += count - 1;
	}
	return lineCount;
    }

    private int parseIfSection(ParserTree tree, SourceCode sourceCode,
	    int lineNum) throws TreeException, ParserException {
	ParserTree ifSection = new ParserTree("if-section");
	tree.addChild(ifSection);
	int lineCount = 0;
	parseNonTextLine(ifGroup, ifSection, sourceCode, lineNum);
	ParserTree ifGroupTree = ifSection.getChild("if-group");
	lineCount++;
	// parse group
	ParserTree group = new ParserTree("group");
	int lines = parse(group, sourceCode, lineNum + 1);
	if (lines > 0) {
	    ifGroupTree.addChild(group);
	    lineCount += lines;
	}
	do {
	    if (parseOptional(elifGroupParser, ifSection, sourceCode, lineNum
		    + lineCount) > 0) {
		List<ParserTree> elifGroups = ifSection
			.getChildren("elif-group");
		ParserTree elifGroup = elifGroups.get(elifGroups.size() - 1);
		lineCount++;
		// parse group
		group = new ParserTree("group");
		lines = parse(group, sourceCode, lineNum + 1);
		if (lines > 0) {
		    elifGroup.addChild(group);
		    lineCount += lines;
		}
	    } else {
		break;
	    }
	} while (true);
	if (parseOptional(elseGroupParser, ifSection, sourceCode, lineNum
		+ lineCount) > 0) {
	    ParserTree elseGroup = ifSection.getChild("else-group");
	    lineCount++;
	    // parse group
	    group = new ParserTree("group");
	    lines = parse(group, sourceCode, lineNum + lineCount);
	    if (lines > 0) {
		elseGroup.addChild(group);
		lineCount += lines;
	    }
	}
	parseNonTextLine(endifLineParser, ifGroupTree, sourceCode, lineNum
		+ lineCount);
	lineCount++;
	return lineCount;
    }

    private int parseContolLine(ParserTree tree, SourceCode sourceCode,
	    int lineNum) throws TreeException, ParserException {
	return parseNonTextLine(controlLineParser, tree, sourceCode, lineNum);
    }

    private int parseNonDirectiveLine(ParserTree tree, SourceCode sourceCode,
	    int lineNum) throws TreeException, ParserException {
	return parseNonTextLine(nonDirectiveLineParser, tree, sourceCode,
		lineNum);
    }

    private int parseTextLine(ParserTree tree, SourceCode sourceCode,
	    int lineNum) throws TreeException, ParserException {

	List<SourceCodeLine> lines = sourceCode.getLines();
	if (lineNum >= lines.size()) {
	    throw new IllegalArgumentException("Line number '" + lineNum
		    + "' is not present.");
	}
	SourceCode codeToParse = new SourceCode();
	SourceCodeLine line;
	int lineCount = 0;
	SourceCodeLine nextLine;
	do {
	    line = lines.get(lineNum + lineCount);
	    codeToParse.addSourceCodeLine(line);
	    lineCount++;
	    if (lineNum + lineCount >= lines.size()) {
		break;
	    }
	    nextLine = lines.get(lineNum + lineCount);
	} while (line.getLine().endsWith("\\\n")
		|| (!DIRECTIVE_LINE_START.matcher(nextLine.getLine()).find()));
	/*
	 * Next we check for an empty line which might not get parsed by the
	 * grammar.
	 */
	if (!hasVisibleTokens(codeToParse)) {
	    ParserTree textLine = new ParserTree("text-line");
	    TokenStream tokenStream = tokenize(codeToParse);
	    for (Token token : tokenStream) {
		textLine.addChild(new ParserTree(token));
	    }
	    tree.addChild(textLine);
	    return lineCount;
	}
	ParserTree parserTree = textGroupParser.parse(codeToParse);
	tree.addChildren(parserTree.getChildren());
	return lineCount;
    }

    private int parseOptional(PackratParser parser, ParserTree tree,
	    SourceCode sourceCode, int lineNum) throws TreeException {
	try {
	    return parseNonTextLine(parser, tree, sourceCode, lineNum);
	} catch (ParserException e) {
	    return 0;
	}
    }

    private int parseNonTextLine(PackratParser parser, ParserTree tree,
	    SourceCode sourceCode, int lineNum) throws TreeException,
	    ParserException {
	List<SourceCodeLine> lines = sourceCode.getLines();
	if (lineNum >= lines.size()) {
	    throw new IllegalArgumentException("Line number '" + lineNum
		    + "' is not present.");
	}
	SourceCode codeToParse = new SourceCode();
	SourceCodeLine line;
	int lineCount = 0;
	do {
	    line = lines.get(lineNum + lineCount);
	    codeToParse.addSourceCodeLine(line);
	    lineCount++;
	} while (line.getLine().endsWith("\\\n"));
	ParserTree parserTree = parser.parse(codeToParse);
	tree.addChildren(parserTree.getChildren());
	return lineCount;
    }

    private boolean hasVisibleTokens(SourceCode codeToParse)
	    throws ParserException {
	for (Token token : tokenize(codeToParse)) {
	    if (token.getVisibility() == Visibility.VISIBLE) {
		return true;
	    }
	}
	return false;
    }

    private TokenStream tokenize(SourceCode codeToParse) throws ParserException {
	try {
	    Lexer lexer = new RegExpLexer(C11Grammar.getGrammar());
	    return lexer.lex(codeToParse);
	} catch (LexerException e) {
	    throw new ParserException("Could not lex the source code.", e);
	}
    }
}
