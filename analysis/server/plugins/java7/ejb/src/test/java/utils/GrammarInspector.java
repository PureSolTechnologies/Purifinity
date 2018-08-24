package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.grammar.GrammarException;
import com.puresoltechnologies.parsers.parser.ParserFactory;
import com.puresoltechnologies.parsers.parser.ParserFactoryException;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;

public class GrammarInspector {

	public static void main(String args[]) throws IOException,
			GrammarException, ParserFactoryException {
		Grammar grammar = JavaGrammar.getGrammar();

		ParserFactory.create(grammar).generateInspectionInformation(
				new File("grammar_inspection"));
	}
}
