package utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;
import com.puresol.utils.PersistenceException;

public class GrammarInspector {

	public static void main(String args[]) {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);

			Grammar grammar = JavaGrammar.getInstance().getGrammar();

			ParserFactory.create(grammar).generateInspectionInformation(
					new File("grammar_inspection"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (GrammarException e) {
			e.printStackTrace();
		} catch (ParserFactoryException e) {
			e.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
}
