package utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerFactory;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;
import com.puresol.utils.packages.PackageBuilderUtils;
import com.puresol.utils.packages.PackageDirectory;

public class PreparePackageResources {

    private static final Logger logger = LoggerFactory
	    .getLogger(PreparePackageResources.class);

    public static void main(String args[]) {
	try {
	    PackageBuilderUtils.createPackageDirectory(PackageDirectory.RES,
		    new File(JavaGrammar.GRAMMAR_RESOURCE).getParentFile());

	    logger.info("Reading and persisting grammar...");
	    GrammarReader grammarReader = new GrammarReader(
		    JavaGrammar.class
			    .getResourceAsStream(JavaGrammar.GRAMMAR_RESOURCE));
	    try {
		Grammar grammar = grammarReader.getGrammar();
		PackageBuilderUtils.persistObject(PackageDirectory.RES,
			new File(JavaGrammar.PERSISTED_GRAMMAR_RESOURCE),
			grammar);
		logger.info("done.");

		logger.info("Creating lexer...");
		Lexer lexer = LexerFactory.create(grammar);
		PackageBuilderUtils.persistObject(PackageDirectory.RES,
			new File(JavaGrammar.PERSISTED_LEXER_RESOURCE), lexer);
		logger.info("done.");

		logger.info("Creating parser...");
		Parser parser = ParserFactory.create(grammar);
		PackageBuilderUtils
			.persistObject(PackageDirectory.RES, new File(
				JavaGrammar.PERSISTED_PARSER_RESOURCE), parser);
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
