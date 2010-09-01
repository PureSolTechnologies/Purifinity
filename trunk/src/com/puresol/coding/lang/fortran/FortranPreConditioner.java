package com.puresol.coding.lang.fortran;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;

/**
 * This is the preconditioner for Fortran. The file input is conditioned here
 * for later lexing. The main purpose is to break the line formatting into a
 * text stream by creating some first hidden tokens.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranPreConditioner {

	private static final Logger logger = Logger
			.getLogger(FortranPreConditioner.class);

	public static Pattern getLineLeadPattern() {
		return Pattern.compile("^([ C*!])([ \\d!]{4}[ \\d!;$])");
	}

	private final File file;
	private final TokenStream tokenStream = new TokenStream();

	private int currentPosition = 0;
	private int currentIndex = 0;
	private int currentLine = 1;

	public FortranPreConditioner(File file) throws IOException {
		this.file = file;
		generateTokenStream();
	}

	private void generateTokenStream() throws IOException {
		List<String> buffer = readToBuffer();
		preconditioner(buffer);
	}

	private List<String> readToBuffer() {
		List<String> buffer = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.add(line);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return buffer;
	}

	private void preconditioner(List<String> buffer) {
		Pattern lineLeadPattern = getLineLeadPattern();
		for (String line : buffer) {
			Matcher matcher = lineLeadPattern.matcher(line);
			if (matcher.find()) {
				String lineLead = matcher.group();
				boolean isComment = matcher.group(0).getBytes()[0] != ' ';
				String label = matcher.group(1);
				boolean isLabel = !label.isEmpty();
				boolean lineContinuation = (!matcher.group(2).equals(" "))
						&& (!matcher.group(2).equals("0"));
				if (isLabel) {
					// TODO
				}
				if (!lineContinuation) {
					// TODO
				}
				if (isComment) {
					addCommentLine(line);
				} else {
					addLineLead(lineLead);
					addLine(line.substring(6));
				}
			} else {
				addLine(line);
			}
			currentLine++;
		}
	}

	private void addLineLead(String line) {
		Token token = new Token("LineLead", line, Visibility.HIDDEN);
		tokenStream.add(token);
		currentIndex++;
		currentPosition += line.length();
	}

	private void addLine(String line) {
		Token token = new Token("", line, Visibility.VISIBLE);
		tokenStream.add(token);
		currentIndex++;
		currentPosition += line.length();
	}

	private void addCommentLine(String line) {
		Token token = new Token("COMMENT", line, Visibility.HIDDEN);
		tokenStream.add(token);
		currentIndex++;
		currentPosition += line.length();
	}

	/**
	 * @return the tokenStream
	 */
	public TokenStream getTokenStream() {
		return tokenStream;
	}
}
