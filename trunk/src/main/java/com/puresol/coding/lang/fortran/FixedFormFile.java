package com.puresol.coding.lang.fortran;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
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

/**
 * This class checks source files for matching the fixed form and converts them
 * into a special token stream by cutting the leading six charactes away.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FixedFormFile {

	/**
	 * This pattern checks the beginning of a line for a comment line. A comment
	 * in fixed form is starting with 'C' (also 'c' for case-insensitivity) or a
	 * '*'. If a '!' is found within the first five characters, the line is also
	 * a comment line.
	 */
	private static final Pattern COMMENT_PATTERN = Pattern
			.compile("^([Cc*]|[ ]{0,4}!)");

	/**
	 * This pattern checks for a label in front of a line. The line needs to
	 * start with a space ' '. Within the next four characters an integer needs
	 * to be found. The sixth character is allowed to be zero.
	 */
	private static final Pattern LABEL_PATTERN = Pattern.compile("^("
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
			+ "|" + " \\d\\d\\d\\d[ 0]" // four digit integer 2nd position
			+ "|" + "\\d\\d\\d\\d\\d[ 0]" // five digit integer 2nd position
			+ ")"); // last character is a space or zero

	/**
	 * This pattern checks for a line continuation. If this is the case the
	 * first five characters at a line need to be spaces ' '. The sixth
	 * character is needed to be not a space or a zero. Then the line is a line
	 * continue. In this case the last LINE_TERMINATOR or LINE_COMMENT is set to
	 * ignored.
	 */
	private static final Pattern CONTINUATION_PATTERN = Pattern
			.compile("^     [^ 0]");

	/**
	 * This pattern checks for six spaces at the line beginning. If this is the
	 * case, the six spaces are treaded as simple whitespace.
	 */
	private static final Pattern EMPTY_PATTERN = Pattern.compile("^[ ]{6}");

	/**
	 * This pattern checks for a blank line containing only white spaces. If
	 * this is the case, the line is treaded as white space.
	 * 
	 * The Fortran Language Reference tells to tread the line as comment, but
	 * without any content (even without the explicit C comment start), it's
	 * less misleading to tread the line a white space.
	 */
	private static final Pattern BLANK_LINE_PATTERN = Pattern
			.compile("^[ \\t]*$");

	private final File file;
	private final boolean valid;
	private final List<Integer> invalidLines = new ArrayList<Integer>();

	public FixedFormFile(File file) throws IOException {
		super();
		this.file = file;
		valid = validate();
	}

	public boolean validate() throws IOException {
		BufferedReader reader = null;
		boolean isFixedForm = true;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			int lineId = 0;
			while ((line = reader.readLine()) != null) {
				lineId++;
				if (line.isEmpty()) {
					continue;
				}
				if (!(EMPTY_PATTERN.matcher(line).find()
						|| COMMENT_PATTERN.matcher(line).find()
						|| LABEL_PATTERN.matcher(line).find()
						|| CONTINUATION_PATTERN.matcher(line).find() || BLANK_LINE_PATTERN
						.matcher(line).find())) {
					isFixedForm = false;
					invalidLines.add(lineId);
				}
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return isFixedForm;
	}

	public boolean isValid() {
		return valid;
	}

	public List<Integer> getInvalidLines() {
		return invalidLines;
	}

	public TokenStream scan(Lexer lexer) throws IOException, LexerException {
		TokenStream tokenStream = new TokenStream(file.toString());
		BufferedReader reader = null;
		int id = 0;
		int pos = 0;
		int lineId = 1;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				line += "\n";
				TokenStream subTokenStream;
				if (EMPTY_PATTERN.matcher(line).find()) {
					Matcher matcher = EMPTY_PATTERN.matcher(line);
					matcher.find();
					tokenStream.add(new Token("WHITESPACE", matcher.group(),
							Visibility.IGNORED, new TokenMetaData(file
									.toString(), id, pos, lineId, 1)));
					id++;
					pos += 6;
					subTokenStream = lexer.lex(
							new StringReader(line.substring(6)),
							file.toString());
				} else if (COMMENT_PATTERN.matcher(line).find()) {
					tokenStream.add(new Token("COMMENT_LINE", line,
							Visibility.IGNORED, new TokenMetaData(file
									.toString(), id, pos, lineId, 2)));
					id++;
					pos += line.length();
					lineId++;
					subTokenStream = new TokenStream(file.toString());
				} else if (BLANK_LINE_PATTERN.matcher(line).find()) {
					tokenStream.add(new Token("WHITESPACE", line,
							Visibility.IGNORED, new TokenMetaData(file
									.toString(), id, pos, lineId, 2)));
					id++;
					pos += line.length();
					lineId++;
					subTokenStream = new TokenStream(file.toString());
				} else if (LABEL_PATTERN.matcher(line).find()) {
					Matcher matcher = LABEL_PATTERN.matcher(line);
					matcher.find();
					tokenStream.add(new Token("LABEL", matcher.group(),
							Visibility.IGNORED, new TokenMetaData(file
									.toString(), id, pos, lineId, 1)));
					id++;
					pos += 6;
					subTokenStream = lexer.lex(
							new StringReader(line.substring(6)),
							file.toString());
				} else if (CONTINUATION_PATTERN.matcher(line).find()) {
					ignoreLastLineTerminator(tokenStream);
					Matcher matcher = CONTINUATION_PATTERN.matcher(line);
					matcher.find();
					tokenStream.add(new Token("LINE_CONCATATION", matcher
							.group(), Visibility.IGNORED, new TokenMetaData(
							file.toString(), id, pos, lineId, 1)));
					id++;
					pos += 6;
					subTokenStream = lexer.lex(
							new StringReader(line.substring(6)),
							file.toString());
				} else if (line.equals("\n")) {
					tokenStream.add(new Token("LINE_TERMINATOR", "\n",
							Visibility.VISIBLE, new TokenMetaData(file
									.toString(), id, pos, lineId, 1)));
					id++;
					pos += 1;
					lineId++;
					subTokenStream = new TokenStream(file.toString());
				} else {
					throw new IOException(
							"Invalid file! It does not contain Fortran fixed form format at line "
									+ lineId + "!");
				}
				for (Token token : subTokenStream) {
					TokenMetaData metaData = token.getMetaData();
					Token newToken = new Token(token.getName(),
							token.getText(), token.getVisibility(),
							new TokenMetaData(metaData.getSourceName(), id
									+ metaData.getId(),
									pos + metaData.getPos(), lineId
											+ metaData.getLine() - 1,
									metaData.getLineNum()));
					tokenStream.add(newToken);
				}
				Token lastToken = tokenStream.get(tokenStream.size() - 1);
				TokenMetaData metaData = lastToken.getMetaData();
				id = metaData.getId() + 1;
				pos = metaData.getPos() + lastToken.getText().length();
				lineId = metaData.getLine() + metaData.getLineNum() - 1;
			}
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return tokenStream;
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
		if (valid) {
			return file.toString() + " is valid Fortran fixed form.";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(file.toString());
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
