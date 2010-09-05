package utilities;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserManager;
import com.puresol.uhura.parser.lr.LR1Parser;

public class CreateParser {

	public static void main(String args[]) {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		Grammar grammar;
		try {
			grammar = FortranGrammar.get();
			Parser parser = new LR1Parser(grammar);
			ParserManager.storeParser(new File(
					"src/com/puresol/coding/lang/fortran/parser"),
					"Fortran2008", parser);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
