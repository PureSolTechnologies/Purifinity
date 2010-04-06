package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.source.VirtualStatementEnd;
import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.coding.tokentypes.Comment;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;

public class FortranPreConditioner extends DefaultPreConditioner {

	private static final Logger logger = Logger
			.getLogger(FortranPreConditioner.class);

	private static final Pattern lineLeadPattern = Pattern
			.compile("^([^\\t\\r\\n])([^\\t\\r\\n]{4})([^\\t\\r\\n])");

	public static Pattern getLineLeadPattern() {
		return lineLeadPattern;
	}

	int counter = 0;
	int pos = 0;
	int lineNum = 0;

	public FortranPreConditioner(File directory, File file)
			throws FileNotFoundException {
		super(directory, file);
	}

	@Override
	protected void generateTokenStream() throws IOException {
		ArrayList<String> buffer = readToBuffer();
		preconditioner(buffer);
	}

	private ArrayList<String> readToBuffer() {
		ArrayList<String> buffer = new ArrayList<String>();
		String line;
		while ((line = readLine()) != null) {
			buffer.add(line);
		}
		return buffer;
	}

	private void preconditioner(ArrayList<String> buffer) {
		try {
			for (String line : buffer) {
				Matcher matcher = getLineLeadPattern().matcher(line);
				if (matcher.find()) {
					String lineLead = matcher.group();
					boolean comment = matcher.group(0).getBytes()[0] != ' ';
					// String label = matcher.group(1);
					boolean lineContinuation = !matcher.group(2).equals(" ");
					if (!lineContinuation) {
						addLineBreak();
					}
					if (comment) {
						addCommentLine(line);
					} else {
						addLineLead(lineLead);
						addLine(line.substring(6));
					}
				} else {
					if (Pattern.compile("^\\S").matcher(line).find()) {
						addLineBreak();
						addCommentLine(line);
					} else {
						addLineBreak();
						addLine(line);
					}
				}
				lineNum++;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addLineBreak() throws IOException {
		addToken(new Token(counter, TokenPublicity.ADDED, pos, 0, "", lineNum,
				lineNum, VirtualStatementEnd.class));
	}

	private void addLineLead(String line) throws IOException {
		addToken(new Token(counter, TokenPublicity.HIDDEN, pos, line.length(),
				line, lineNum, lineNum + 1, LineLead.class));
	}

	private void addLine(String line) throws IOException {
		addToken(new Token(counter, TokenPublicity.VISIBLE, pos, line.length(),
				line, lineNum, lineNum + 1, null));
	}

	private void addCommentLine(String line) throws IOException {
		addToken(new Token(counter, TokenPublicity.HIDDEN, pos, line.length(),
				line, lineNum, lineNum + 1, Comment.class));
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
