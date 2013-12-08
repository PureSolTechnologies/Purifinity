package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.utils.packages.PackageBuilderUtils;
import com.puresoltechnologies.commons.utils.packages.PackageDirectory;
import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.grammar.GrammarReader;
import com.puresoltechnologies.parser.impl.lexer.Lexer;
import com.puresoltechnologies.parser.impl.lexer.LexerFactory;
import com.puresoltechnologies.parser.impl.lexer.LexerFactoryException;
import com.puresoltechnologies.parser.impl.parser.Parser;
import com.puresoltechnologies.parser.impl.parser.ParserFactory;
import com.puresoltechnologies.parser.impl.parser.ParserFactoryException;
import com.puresoltechnologies.purifinity.coding.lang.test.grammar.TestLanguageGrammar;

public class PreparePackageResources {

	private static final Logger logger = LoggerFactory
			.getLogger(PreparePackageResources.class);

	public static void main(String args[]) {
		try {
			PackageBuilderUtils.createPackageDirectory(PackageDirectory.RES,
					new File(TestLanguageGrammar.GRAMMAR_RESOURCE)
							.getParentFile());

			logger.info("Reading and persisting grammar...");
			InputStream grammerResource = TestLanguageGrammar.class
					.getResourceAsStream(TestLanguageGrammar.GRAMMAR_RESOURCE);
			if (grammerResource == null) {
				throw new RuntimeException(
						"Could not open teat language grammar '"
								+ TestLanguageGrammar.GRAMMAR_RESOURCE + "'!");
			}
			try {
				GrammarReader grammarReader = new GrammarReader(grammerResource);
				try {
					Grammar grammar = grammarReader.getGrammar();
					PackageBuilderUtils
							.persistObject(
									PackageDirectory.RES,
									new File(
											TestLanguageGrammar.PERSISTED_GRAMMAR_RESOURCE),
									grammar);
					logger.info("done.");

					logger.info("Creating lexer...");
					Lexer lexer = LexerFactory.create(grammar);
					PackageBuilderUtils
							.persistObject(
									PackageDirectory.RES,
									new File(
											TestLanguageGrammar.PERSISTED_LEXER_RESOURCE),
									lexer);
					logger.info("done.");

					logger.info("Creating parser...");
					Parser parser = ParserFactory.create(grammar);
					PackageBuilderUtils
							.persistObject(
									PackageDirectory.RES,
									new File(
											TestLanguageGrammar.PERSISTED_PARSER_RESOURCE),
									parser);
					logger.info("done.");
				} finally {
					grammarReader.close();
				}
			} finally {
				grammerResource.close();
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
