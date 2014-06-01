package utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.grammar.GrammarException;
import com.puresoltechnologies.parsers.grammar.GrammarReader;
import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.lexer.LexerFactory;
import com.puresoltechnologies.parsers.lexer.LexerFactoryException;
import com.puresoltechnologies.parsers.parser.Parser;
import com.puresoltechnologies.parsers.parser.ParserFactory;
import com.puresoltechnologies.parsers.parser.ParserFactoryException;
import com.puresoltechnologies.purifinity.framework.commons.utils.packages.PackageBuilderUtils;
import com.puresoltechnologies.purifinity.framework.commons.utils.packages.PackageDirectory;
import com.puresoltechnologies.purifinity.server.plugin.c11.grammar.C11Grammar;

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
				File grammarPersistenceFile = new File(
						C11Grammar.PERSISTED_GRAMMAR_RESOURCE);
				PackageBuilderUtils.persistObject(PackageDirectory.RES,
						grammarPersistenceFile, grammar);
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