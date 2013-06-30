package utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.purifinity.coding.lang.c11.grammar.C11Grammar;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarException;
import com.puresol.purifinity.uhura.grammar.GrammarReader;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.LexerFactory;
import com.puresol.purifinity.uhura.lexer.LexerFactoryException;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserFactory;
import com.puresol.purifinity.uhura.parser.ParserFactoryException;
import com.puresol.purifinity.utils.packages.PackageBuilderUtils;
import com.puresol.purifinity.utils.packages.PackageDirectory;

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
