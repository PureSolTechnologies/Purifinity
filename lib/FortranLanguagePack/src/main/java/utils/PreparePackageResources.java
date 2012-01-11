package utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
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
import com.puresol.utils.PersistenceException;

public class PreparePackageResources {

	private static final Logger logger = Logger
			.getLogger(PreparePackageResources.class);

	public static void main(String args[]) {
		try {
			PackageBuilderUtils.createPackageDirectory(PackageDirectory.RES,
					new File(FortranGrammar.GRAMMAR_RESOURCE).getParentFile());

			logger.info("Reading and persisting grammar...");
			GrammarReader grammarReader = new GrammarReader(
					FortranGrammar.class
							.getResourceAsStream(FortranGrammar.GRAMMAR_RESOURCE));
			Grammar grammar = grammarReader.getGrammar();
			PackageBuilderUtils.persistObject(PackageDirectory.RES, new File(
					FortranGrammar.PERSISTED_GRAMMAR_RESOURCE), grammar);
			logger.info("done.");

			logger.info("Creating lexer...");
			Lexer lexer = LexerFactory.create(grammar);
			PackageBuilderUtils.persistObject(PackageDirectory.RES, new File(
					FortranGrammar.PERSISTED_LEXER_RESOURCE), lexer);
			logger.info("done.");

			logger.info("Creating parser...");
			Parser parser = ParserFactory.create(grammar);
			PackageBuilderUtils.persistObject(PackageDirectory.RES, new File(
					FortranGrammar.PERSISTED_PARSER_RESOURCE), parser);
			logger.info("done.");
		} catch (GrammarException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (LexerFactoryException e) {
			e.printStackTrace();
		} catch (ParserFactoryException e) {
			e.printStackTrace();
		}
	}
}
