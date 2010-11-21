package com.puresol.uhura.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.ast.ASTException;

/**
 * This class is for reading Nyota Uhura grammar files. The grammar file is read
 * and interpreted. The resulting grammar can be used for processing with Nyota
 * Uhura afterwards.
 * 
 * This reader reads the grammar files and generates a Properties field with all
 * grammar properties, a TokenDefinitionSet with all token definitions found and
 * a ProductionSet with all BNF productions converted from the EBNF in the
 * grammar file.
 * 
 * This is performed by the joined functionality from GrammarFile and GrammarConverter.
 * 
 * @see GrammarFile
 * @see GrammarConverter
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarReader {

	private final AST ast;
	private final GrammarConverter converter;

	/**
	 * Constructor for InputStream reading.
	 * 
	 * @param inputStream
	 * @throws IOException
	 * @throws GrammarException
	 * @throws ASTException
	 */
	public GrammarReader(InputStream inputStream) throws GrammarException,
			IOException {
		this(new InputStreamReader(inputStream));
	}

	/**
	 * Constructor taking a reader for reading the grammar.
	 * 
	 * @param reader
	 * @throws GrammarException
	 * @throws ASTException
	 * @throws IOException
	 */
	public GrammarReader(Reader reader) throws GrammarException, IOException {
		ast = new GrammarFile(reader).getAST();
		try {
			converter = new GrammarConverter(ast);
		} catch (ASTException e) {
			throw new IOException("Uhura grammar AST is broken!!!");
		}
	}

	public AST getAST() {
		return ast;
	}

	public Grammar getGrammar() {
		return converter.getGrammar();
	}

}
