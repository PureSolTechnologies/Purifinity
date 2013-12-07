package com.puresoltechnologies.parser.impl.grammar;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.puresoltechnologies.commons.trees.TreeException;
import com.puresoltechnologies.parser.impl.parser.ParserTree;

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
 * This is performed by the joined functionality from GrammarFile and
 * GrammarConverter.
 * 
 * @see GrammarFile
 * @see GrammarConverter
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarReader implements Closeable {

	private final ParserTree ast;
	private final GrammarConverter converter;
	private final GrammarFile grammarFile;

	/**
	 * Constructor for InputStream reading.
	 * 
	 * @param inputStream
	 * @throws IOException
	 * @throws GrammarException
	 * @throws TreeException
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
	 * @throws TreeException
	 * @throws IOException
	 */
	public GrammarReader(Reader reader) throws GrammarException, IOException {
		grammarFile = new GrammarFile(reader);
		ast = grammarFile.getAST();
		converter = new GrammarConverter(ast);
	}

	public ParserTree getAST() {
		return ast;
	}

	public Grammar getGrammar() {
		return converter.getGrammar();
	}

	@Override
	public void close() throws IOException {
		grammarFile.close();
	}

}
