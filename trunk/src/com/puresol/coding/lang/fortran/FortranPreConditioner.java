package com.puresol.coding.lang.fortran;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swingx.data.LineEnd;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.coding.lang.fortran.source.symbols.LineBreak;
import com.puresol.coding.tokentypes.Comment;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public class FortranPreConditioner extends DefaultPreConditioner {

	private static final Logger logger = Logger
			.getLogger(FortranPreConditioner.class);

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
		InputStream stream = getStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		String line = reader.readLine();
		if (line.startsWith("     ")) {
			preconditionFortran77(reader, line);
		} else {
			defaultPreConditioner(reader, line);
		}
	}

	private void preconditionFortran77(BufferedReader reader, String line) {
		try {
			int counter = 0;
			int pos = 0;
			int lineNum = 0;
			while (line != null) {
				System.out.println(line);
				System.out.println(line.length());
				if (line.length() == 1) {
					Token token = new Token(counter, TokenPublicity.HIDDEN,
							pos, line.length(), line, lineNum, lineNum,
							LineBreak.class);
					getTokenStream().addToken(token);
					counter++;
					lineNum++;
					pos++;
					line = reader.readLine();
					continue;
				}
				String lead = line.substring(0, 5);
				line = line.substring(6);
				Token token = new Token(counter, TokenPublicity.HIDDEN, pos,
						lead.length(), lead, lineNum, lineNum, LineLead.class);
				getTokenStream().addToken(token);
				pos += lead.length();
				counter++;
				if (lead.startsWith("*") || lead.startsWith("c")
						|| lead.startsWith("C")) {
					token = new Token(counter, TokenPublicity.HIDDEN, pos, line
							.length(), line, lineNum, lineNum, Comment.class);
				} else {
					token = new Token(counter, TokenPublicity.VISIBLE, pos,
							line.length(), line, lineNum, lineNum, null);
				}
				getTokenStream().addToken(token);
				pos += line.length();
				counter++;
				lineNum++;
				line = reader.readLine();
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void defaultPreConditioner(BufferedReader reader, String line) {
		try {
			String text = "";
			while (line != null) {
				text += line + "\n";
				line = reader.readLine();
			}
			Token token = new Token(0, TokenPublicity.VISIBLE, 0,
					text.length(), text, 0, text
							.split(LineEnd.UNIX.getString()).length - 1, null);
			getTokenStream().addToken(token);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
