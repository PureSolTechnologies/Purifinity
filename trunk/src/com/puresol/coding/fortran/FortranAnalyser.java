package com.puresol.coding.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.apache.log4j.Logger;

import com.puresol.coding.AbstractAnalyser;
import com.puresol.coding.Language;
import com.puresol.coding.fortran.antlr.output.FortranLexer;

public class FortranAnalyser extends AbstractAnalyser {
	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private FortranLexer lexer = null;
	private int lineNumber = 0;
	ArrayList<Integer> lineLengths = null;
	ArrayList<Integer> lineLengthsTrimmed = null;

	public static boolean isSuitable(File file) {
		return (file.getPath().endsWith(".f")
				|| file.getPath().endsWith(".f77")
				|| file.getPath().endsWith(".f90") || file.getPath().endsWith(
				".f95"));
	}

	/**
	 * This is the default constructor.
	 * 
	 * @param A
	 *            file to be analysed.
	 */
	public FortranAnalyser(File projectDirectory, File file) {
		super(projectDirectory, file);
		parse();
	}

	private void parse() {
		try {
			InputStream in = new FileInputStream(getProjectDirectory()
					.toString()
					+ "/" + getFile().toString());
			lexer = new FortranLexer(new ANTLRInputStream(in));
			CommonTokenStream stream = new CommonTokenStream(lexer);
			for (Token token : (List<Token>) stream.getTokens()) {
				if (token.getChannel() == Token.HIDDEN_CHANNEL) {
					System.out.print("hidden: ");
				}
				System.out.print("'");
				System.out.print(token.getText());
				System.out.print("'");
				System.out.println("\t(" + token.getLine() + ")");
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected void calculate() {
		clearAllMetrics();
		// TODO
	}

	public Language getLanguage() {
		return Language.FORTRAN;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

	public static void main(String[] args) {
		new FortranAnalyser(new File("."), new File(
				"src/com/puresol/coding/fortran/antlr/scopy.f"));
	}
}
