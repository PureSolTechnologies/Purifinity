package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.puresol.coding.tokentypes.Comment;
import com.puresol.parser.preconditioner.AbstractPreConditioner;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenCreationException;

/**
 * This is the preconditioner for Fortran. The file input is conditioned here
 * for later lexing. The main purpose is to break the line formatting into a
 * text stream by creating some first hidden tokens.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranPreConditioner extends AbstractPreConditioner {

	private static final Logger logger = Logger
			.getLogger(FortranPreConditioner.class);

	public static Pattern getLineLeadPattern() {
		return Pattern
				.compile("^([^\\t\\r\\n])([^\\t\\r\\n]{4})([^\\t\\r\\n])");
	}

	private int currentPosition = 0;
	private int currentIndex = 0;
	private int currentLine = 1;

	public FortranPreConditioner(File file) throws IOException {
		super(file);
	}

	@Override
	protected void generateTokenStream() throws IOException {
		List<String> buffer = readToBuffer();
		preconditioner(buffer);
	}

	private List<String> readToBuffer() {
		List<String> buffer = new ArrayList<String>();
		String line;
		while ((line = readLine()) != null) {
			buffer.add(line);
		}
		return buffer;
	}

	private void preconditioner(List<String> buffer) {
		try {
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
		} catch (TokenCreationException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addLineLead(String line) throws TokenCreationException {
		addToken(Token.createByDefinition(LineLead.class, currentIndex,
				currentPosition, currentLine, line));
		currentIndex++;
		currentPosition += line.length();
	}

	private void addLine(String line) {
		addToken(Token.createPrimitiveFromString(currentIndex, currentPosition,
				currentLine, line));
		currentIndex++;
		currentPosition += line.length();
	}

	private void addCommentLine(String line) throws TokenCreationException {
		addToken(Token.createByDefinition(Comment.class, currentIndex,
				currentPosition, currentLine, line));
		currentIndex++;
		currentPosition += line.length();
	}

	private String readLine() {
		try {
			String line = "";
			InputStream stream = getInputStream();
			int data;
			do {
				data = stream.read();
				if (data >= 0) {
					line += Character.valueOf((char) data);
				} else {
					if (line.isEmpty()) {
						return null;
					}
					return line;
				}
			} while ((char) data != '\n');
			return line;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
