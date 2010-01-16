package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swingx.data.LineEnd;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.source.VirtualStatementEnd;
import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.coding.lang.fortran.source.symbols.WhiteSpace;
import com.puresol.coding.tokentypes.Comment;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public class FortranPreConditioner extends DefaultPreConditioner {

	private static final Logger logger = Logger
			.getLogger(FortranPreConditioner.class);

	int counter = 0;
	int pos = 0;
	int lineNum = 0;

	public FortranPreConditioner(File file) throws FileNotFoundException {
		super(file);
	}

	public FortranPreConditioner(String name, InputStream stream) {
		super(name, stream);
	}

	@Override
	protected void generateTokenStream() throws IOException {
		TokenStream tokenStream = new TokenStream(getName());
		setTokenStream(tokenStream);
		String line = readLine();
		if ((line.getBytes()[0] == '*') || (line.getBytes()[0] == 'c')
				|| (line.getBytes()[0] == 'C') || (line.getBytes()[0] == ' ')) {
			preconditionFortran77(line);
		} else {
			defaultPreConditioner(line);
		}
	}

	private void preconditionFortran77(String line) {
		try {
			while (line != null) {
				if (line.getBytes()[0] != ' ') {
					processCommentLine(line);
				} else if (line.length() <= 6) {
					processLineLead(line);
				} else {
					processNormalLine(line);
				}
				lineNum++;
				line = readLine();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void processCommentLine(String line) throws IOException {
		Token token = new Token(counter, TokenPublicity.HIDDEN, pos, line
				.length(), line, lineNum, lineNum + 1, Comment.class);
		getTokenStream().addToken(token);
		pos += line.length();
		counter++;
	}

	private void addToken(Token token) throws IOException {
		getTokenStream().addToken(token);
		pos += token.getText().length();
		counter++;
	}

	private void processLineLead(String line) throws IOException {
		if (line.length() == 6) {
			byte[] bytes = line.getBytes();
			if (bytes[5] == ' ') {
				addToken(new Token(counter, TokenPublicity.ADDED, pos, 0, "",
						lineNum, lineNum, VirtualStatementEnd.class));
			}
		}
		addToken(new Token(counter, TokenPublicity.VISIBLE, pos, line.length(),
				line, lineNum, lineNum + 1, LineLead.class));
	}

	private void processNormalLine(String line) throws IOException {
		String lead = line.substring(0, 5);
		line = line.substring(6);
		processLineLead(lead);
		addToken(new Token(counter, TokenPublicity.VISIBLE, pos, line.length(),
				line, lineNum, lineNum, null));
	}

	private void defaultPreConditioner(String line) {
		try {
			String text = "";
			while (line != null) {
				text += line;
				line = readLine();
			}
			addToken(new Token(0, TokenPublicity.VISIBLE, 0, text.length(),
					text, 0, text.split(LineEnd.UNIX.getString()).length - 1,
					null));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String readLine() {
		try {
			String line = "";
			InputStream stream = getStream();
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
