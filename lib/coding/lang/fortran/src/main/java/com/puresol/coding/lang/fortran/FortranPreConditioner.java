package com.puresol.coding.lang.fortran;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.FixedCodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

/**
 * This class checks source files for matching the fixed form and converts them
 * into a special token stream by cutting the leading six charactes away.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranPreConditioner {

    /**
     * This pattern checks the beginning of a line for a comment line. A comment
     * in fixed form is starting with 'C' (also 'c' for case-insensitivity) or a
     * '*'. If a '!' is found within the first five characters, the line is also
     * a comment line.
     */
    private static final Pattern FIXED_FORM_COMMENT_PATTERN = Pattern
	    .compile("^([Cc*]|[ ]{0,4}!)");

    /**
     * This pattern checks for a label in front of a line. The line needs to
     * start with a space ' '. Within the next four characters an integer needs
     * to be found. The sixth character is allowed to be zero.
     */
    private static final Pattern FIXED_FORM_LABEL_PATTERN = Pattern
	    .compile("^("
	    /*          */+ "\\d     " // one digit integer 1st position
		    + "|" + " \\d    " // one digit integer 2nd position
		    + "|" + "  \\d   " // one digit integer 3rd position
		    + "|" + "   \\d  " // one digit integer 4th position
		    + "|" + "    \\d[ 0]" // one digit integer 5th position
		    + "|" + "\\d\\d    " // two digit integer 1st position
		    + "|" + " \\d\\d   " // two digit integer 2nd position
		    + "|" + "  \\d\\d  " // two digit integer 3rd position
		    + "|" + "   \\d\\d[ 0]" // two digit integer 4th position
		    + "|" + "\\d\\d\\d   " // three digit integer 1st position
		    + "|" + " \\d\\d\\d  " // three digit integer 2nd position
		    + "|" + "  \\d\\d\\d[ 0]" // three digit integer 3rd
					      // position
		    + "|" + "\\d\\d\\d\\d  " // four digit integer 1st position
		    + "|" + " \\d\\d\\d\\d[ 0]" // four digit integer 2nd
						// position
		    + "|" + "\\d\\d\\d\\d\\d[ 0]" // five digit integer 2nd
						  // position
		    + ")"); // last character is a space or zero

    /**
     * This pattern checks for a line continuation. If this is the case the
     * first five characters at a line need to be spaces ' '. The sixth
     * character is needed to be not a space or a zero. Then the line is a line
     * continue. In this case the last LINE_TERMINATOR or LINE_COMMENT is set to
     * ignored.
     */
    private static final Pattern FIXED_FORM_CONTINUATION_PATTERN = Pattern
	    .compile("^     [^ 0\\n\\r\\t]");
    private static final Pattern FREE_FORM_CONTINUATION_PATTERN = Pattern
	    .compile("^[ \\t]*&");
    private static final Pattern FREE_FORM_LABEL_PATTERN = Pattern
	    .compile("^([ \\t]*)(\\d{1,5})([ \\t]+)");

    /**
     * This pattern checks for six spaces at the line beginning. If this is the
     * case, the six spaces are treaded as simple whitespace.
     */
    private static final Pattern FIXED_FORM_EMPTY_PATTERN = Pattern
	    .compile("^[ ]{6}");

    /**
     * This is the fixed form pattern for single quote literal ends in cases of
     * broken literals.
     */
    private static final Pattern FIXED_FORM_SINGLE_QUOTE_LITERAL_END = Pattern
	    .compile("^([^']|'')*'");

    /**
     * This is the fixed form pattern for double quote literal ends in cases of
     * broken literals.
     */
    private static final Pattern FIXED_FORM_DOUBLE_QUOTE_LITERAL_END = Pattern
	    .compile("^([^\"]|\"\")*\"");

    private static final byte BROKEN_CHARACTER_LITERAL_NONE = 0;
    private static final byte BROKEN_CHARACTER_LITERAL_SINGLE_QUOTE = 1;
    private static final byte BROKEN_CHARACTER_LITERAL_DOUBLE_QUOTE = 2;

    private final SourceCode sourceCode;
    private final boolean validFixedForm;
    private final List<Integer> invalidLines = new ArrayList<Integer>();
    private final TokenStream tokenStream;
    private int pos = 0;
    private int lineId = 1;
    private byte currentBrokenCharacterMode = BROKEN_CHARACTER_LITERAL_NONE;

    /**
     * This constructor constructs a new FixedFormFile object on the specified
     * file.
     * 
     * @param sourceCode
     * @throws IOException
     */
    public FortranPreConditioner(SourceCode sourceCode) throws IOException {
	super();
	this.sourceCode = sourceCode;
	tokenStream = new TokenStream();
	validFixedForm = validateFixedForm();
    }

    /**
     * This method checks for a valid fixed form format. It's only check whether
     * a valid line lead is present or not. There is no lexical operation
     * performed.
     * 
     * @return
     * @throws IOException
     */
    private boolean validateFixedForm() throws IOException {
	boolean isFixedForm = true;
	int lineId = 0;
	for (SourceCodeLine sourceLine : sourceCode.getLines()) {
	    String line = sourceLine.getLine();
	    lineId++;
	    if (line.isEmpty()) {
		continue;
	    }
	    if (!(FIXED_FORM_EMPTY_PATTERN.matcher(line).find()
		    || FIXED_FORM_COMMENT_PATTERN.matcher(line).find()
		    || FIXED_FORM_LABEL_PATTERN.matcher(line).find() || FIXED_FORM_CONTINUATION_PATTERN
		    .matcher(line).find())) {
		isFixedForm = false;
		invalidLines.add(lineId);
	    }
	}
	return isFixedForm;
    }

    /**
     * This method returns whether the file has correct line leads or not. There
     * was no lexical operation performed to check the validity of the file.
     * 
     * @return
     */
    public boolean isValidFixedForm() {
	return validFixedForm;
    }

    /**
     * This method returns a list of integers representing the line numbers
     * which are not valid in fixed form manner.
     * 
     * @return
     */
    public List<Integer> getInvalidFixedFormLines() {
	return invalidLines;
    }

    private TokenMetaData getCurrentMetaData(int lineNum) {
	return new TokenMetaData(sourceCode.getLines().get(lineNum - 1)
		.getSource(), lineId, pos, lineNum);
    }

    /**
     * This method performs the lexical scan with the specified lexer.
     * 
     * @param lexer
     * @return
     * @throws IOException
     * @throws LexerException
     */
    public TokenStream scan(Lexer lexer) throws IOException, LexerException {
	reset();
	for (SourceCodeLine line : sourceCode.getLines()) {
	    String text = line.getLine();
	    TokenStream subTokenStream = null;
	    if (FIXED_FORM_EMPTY_PATTERN.matcher(text).find()
		    && (!FREE_FORM_CONTINUATION_PATTERN.matcher(text).find())) {
		subTokenStream = processEmptyPattern(lexer, line);
	    } else if (FIXED_FORM_COMMENT_PATTERN.matcher(text).find()) {
		subTokenStream = new TokenStream();
		processCommentPattern(line);
	    } else if (FIXED_FORM_LABEL_PATTERN.matcher(text).find()) {
		subTokenStream = processLabelPattern(lexer, line);
	    } else if (FIXED_FORM_CONTINUATION_PATTERN.matcher(text).find()) {
		subTokenStream = processContinuationPattern(lexer, line);
	    } else {
		subTokenStream = processFreeForm(lexer, line);
	    }
	    processSubTokenStream(subTokenStream);
	}
	return tokenStream;
    }

    /**
     * This method resets all internal variables and clears the token stream to
     * perform a new scan.
     */
    private void reset() {
	tokenStream.clear();
	pos = 0;
	lineId = 1;
	currentBrokenCharacterMode = BROKEN_CHARACTER_LITERAL_NONE;
    }

    private TokenStream processEmptyPattern(Lexer lexer,
	    SourceCodeLine sourceLine) throws LexerException, IOException {
	if (currentBrokenCharacterMode != BROKEN_CHARACTER_LITERAL_NONE) {
	    throw new LexerException(
		    "Character literal was not closed! Continuation awaited at line "
			    + lineId + "!");
	}
	String line = sourceLine.getLine();
	Matcher matcher = FIXED_FORM_EMPTY_PATTERN.matcher(line);
	matcher.find();
	tokenStream.add(new Token("WHITESPACE", matcher.group(),
		Visibility.IGNORED, new TokenMetaData(sourceLine.getSource(),
			lineId, pos, 1)));
	pos += 6;
	return processBrokenCharacterLiteral(lexer, line.substring(6));
    }

    private void processCommentPattern(SourceCodeLine sourceLine) {
	String line = sourceLine.getLine();
	tokenStream.add(new Token("COMMENT_LINE", line, Visibility.IGNORED,
		new TokenMetaData(sourceLine.getSource(), lineId, pos, 2)));
	pos += line.length();
	lineId++;
    }

    private TokenStream processLabelPattern(Lexer lexer,
	    SourceCodeLine sourceLine) throws LexerException, IOException {
	if (currentBrokenCharacterMode != BROKEN_CHARACTER_LITERAL_NONE) {
	    throw new LexerException(
		    "Character literal was not closed! Continuation awaited at line "
			    + lineId + "!");
	}
	String line = sourceLine.getLine();
	Matcher matcher = FIXED_FORM_LABEL_PATTERN.matcher(line);
	matcher.find();
	tokenStream.add(new Token("LABEL", matcher.group(), Visibility.IGNORED,
		new TokenMetaData(sourceLine.getSource(), lineId, pos, 1)));
	pos += 6;
	return processBrokenCharacterLiteral(lexer, line.substring(6));
    }

    private TokenStream processContinuationPattern(Lexer lexer,
	    SourceCodeLine sourceLine) throws LexerException, IOException {
	ignoreLastLineTerminator(tokenStream);
	String line = sourceLine.getLine();
	Matcher matcher = FIXED_FORM_CONTINUATION_PATTERN.matcher(line);
	matcher.find();
	tokenStream.add(new Token("LINE_CONCATATION", matcher.group(),
		Visibility.IGNORED, new TokenMetaData(sourceLine.getSource(),
			lineId, pos, 1)));
	pos += 6;
	return processBrokenCharacterLiteral(lexer, line.substring(6));
    }

    private TokenStream processFreeForm(Lexer lexer, SourceCodeLine sourceLine)
	    throws LexerException, IOException {
	String line = sourceLine.getLine();
	Matcher matcher = FREE_FORM_CONTINUATION_PATTERN.matcher(line);
	boolean continuation = false;
	if (matcher.find()) {
	    continuation = true;
	    tokenStream.add(new Token("CONTINUATION", matcher.group(),
		    Visibility.IGNORED, getCurrentMetaData(1)));
	    pos += matcher.group().length();
	    line = line.substring(matcher.group().length());
	}
	matcher = FREE_FORM_LABEL_PATTERN.matcher(line);
	if (matcher.find()) {
	    if (continuation) {
		throw new LexerException(
			"Found Label and Continuation in one line. This is not meaning full!");
	    }
	    tokenStream.add(new Token("WHITESPACE", matcher.group(1),
		    Visibility.IGNORED, getCurrentMetaData(1)));
	    tokenStream.add(new Token("LABEL", matcher.group(2),
		    Visibility.IGNORED, getCurrentMetaData(1)));
	    tokenStream.add(new Token("WHITESPACE", matcher.group(3),
		    Visibility.IGNORED, getCurrentMetaData(1)));
	    String string = matcher.group();
	    line = line.substring(string.length());
	}
	if (currentBrokenCharacterMode != BROKEN_CHARACTER_LITERAL_NONE) {
	    if (!continuation) {
		throw new LexerException(
			"Character literal was not closed! Continuation awaited at line "
				+ lineId + "!");
	    }
	}
	return processBrokenCharacterLiteral(lexer, line);
    }

    private TokenStream processBrokenCharacterLiteral(Lexer lexer, String line)
	    throws LexerException, IOException {
	if (currentBrokenCharacterMode == BROKEN_CHARACTER_LITERAL_NONE) {
	    return lexer.lex(new FixedCodeLocation(line).load());
	}
	final Matcher matcher;
	if (currentBrokenCharacterMode == BROKEN_CHARACTER_LITERAL_SINGLE_QUOTE) {
	    matcher = FIXED_FORM_SINGLE_QUOTE_LITERAL_END.matcher(line);
	} else if (currentBrokenCharacterMode == BROKEN_CHARACTER_LITERAL_DOUBLE_QUOTE) {
	    matcher = FIXED_FORM_DOUBLE_QUOTE_LITERAL_END.matcher(line);
	} else {
	    throw new RuntimeException("BrokenCharacterLiteralMode "
		    + currentBrokenCharacterMode + " is unknown!!!");
	}
	if (!matcher.find()) {
	    tokenStream.add(new Token("CHAR_LITERAL_CONSTANT", line,
		    Visibility.VISIBLE, getCurrentMetaData(1)));
	    return new TokenStream();
	}
	tokenStream.add(new Token("CHAR_LITERAL_CONSTANT", matcher.group(),
		Visibility.VISIBLE, getCurrentMetaData(1)));
	currentBrokenCharacterMode = BROKEN_CHARACTER_LITERAL_NONE;
	pos += matcher.group().length();
	return lexer.lex(new FixedCodeLocation(line.substring(matcher.group()
		.length())).load());
    }

    private void processSubTokenStream(TokenStream tokenStream)
	    throws IOException {
	for (Token token : tokenStream) {
	    if ("CHAR_LITERAL_CONSTANT_SINGLE_QUOTE_START".equals(token
		    .getName())) {
		currentBrokenCharacterMode = BROKEN_CHARACTER_LITERAL_SINGLE_QUOTE;
	    }
	    if ("CHAR_LITERAL_CONSTANT_DOUBLE_QUOTE_START".equals(token
		    .getName())) {
		currentBrokenCharacterMode = BROKEN_CHARACTER_LITERAL_DOUBLE_QUOTE;
	    }
	    TokenMetaData metaData = token.getMetaData();
	    Token newToken = new Token(token.getName(), token.getText(),
		    token.getVisibility(),
		    getCurrentMetaData(metaData.getLineNum()));
	    tokenStream.add(newToken);
	    pos += newToken.getText().length();
	    lineId += metaData.getLineNum() - 1;
	}
    }

    /**
     * This method is used after finding a line continuation. The last token
     * which is a signal for a statement end is set to ignored.
     * 
     * Within this algorithm is't not only checked for LINE_TERMINATOR, but also
     * LINE_COMMENT is look for due to constructions like:
     * 
     * <pre>
     * 1:      CALL (A, B,
     * 2:     $C,D, ! comment
     * 3:     $E,F)
     * </pre>
     * 
     * @param tokenStream
     * @throws IOException
     */
    private static void ignoreLastLineTerminator(TokenStream tokenStream)
	    throws IOException {
	int position = tokenStream.size() - 1;
	while (true) {
	    if (position < 0) {
		throw new IOException("Invalid input file!");
	    }
	    Token token = tokenStream.get(position);
	    if (token.getName().equals("LINE_TERMINATOR")
		    || token.getName().equals("LINE_COMMENT")) {
		tokenStream.remove(position);
		tokenStream.add(position,
			new Token(token.getName(), token.getText(),
				Visibility.IGNORED, token.getMetaData()));
		return;
	    }
	    position--;
	}
    }

    @Override
    public String toString() {
	if (validFixedForm) {
	    return sourceCode.toString() + " is valid Fortran fixed form.";
	}
	StringBuffer buffer = new StringBuffer();
	buffer.append(sourceCode.toString());
	buffer.append(" is valid Fortran fixed form!\n");
	buffer.append(invalidLines.size());
	buffer.append(" lines violate contract:\n");
	for (Integer integer : invalidLines) {
	    buffer.append("  - ");
	    buffer.append(integer);
	    buffer.append("\n");
	}
	return buffer.toString();
    }
}
