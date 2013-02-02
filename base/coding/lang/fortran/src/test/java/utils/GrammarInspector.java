package utils;

import java.io.File;
import java.io.IOException;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;

public class GrammarInspector {

    public static void main(String args[]) {
	try {
	    Grammar grammar = FortranGrammar.getInstance();
	    // grammar = grammar
	    // .createWithNewStartProduction("specification-part");

	    ParserFactory.create(grammar).generateInspectionInformation(
		    new File("grammar_inspection"));

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (GrammarException e) {
	    e.printStackTrace();
	} catch (ParserFactoryException e) {
	    e.printStackTrace();
	}
    }
}
