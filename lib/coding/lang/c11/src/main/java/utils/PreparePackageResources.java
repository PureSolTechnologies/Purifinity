package utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.c11.grammar.C11Grammar;
import com.puresol.packages.PackageBuilderUtils;
import com.puresol.packages.PackageDirectory;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerFactory;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;

public class PreparePackageResources {

    private static final Logger logger = LoggerFactory
	    .getLogger(PreparePackageResources.class);

    public static void main(String args[]) {
	try {
	    PackageBuilderUtils.createPackageDirectory(PackageDirectory.RES,
		    new File(C11Grammar.GRAMMAR_RESOURCE).getParentFile());

	    logger.info("Reading and persisting grammar...");
	    GrammarReader grammarReader = new GrammarReader(
		    C11Grammar.class
			    .getResourceAsStream(C11Grammar.GRAMMAR_RESOURCE));
	    try {
		Grammar grammar = grammarReader.getGrammar();
		File grammarPersistenceFile = new File(C11Grammar.PERSISTED_GRAMMAR_RESOURCE);
		PackageBuilderUtils.persistObject(PackageDirectory.RES,
			grammarPersistenceFile,
			grammar);
		logger.info("done.");

		logger.info("Creating lexer...");
		Lexer lexer = LexerFactory.create(grammar);
		PackageBuilderUtils.persistObject(PackageDirectory.RES,
			new File(C11Grammar.PERSISTED_LEXER_RESOURCE), lexer);
		logger.info("done.");

		logger.info("Creating parser...");
		Parser parser = ParserFactory.create(grammar);
		PackageBuilderUtils.persistObject(PackageDirectory.RES,
			new File(C11Grammar.PERSISTED_PARSER_RESOURCE), parser);
		logger.info("done.");
	    } finally {
		grammarReader.close();
	    }
	} catch (GrammarException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (LexerFactoryException e) {
	    e.printStackTrace();
	} catch (ParserFactoryException e) {
	    e.printStackTrace();
	}
    }
}
