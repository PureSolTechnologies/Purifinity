package com.puresol.coding.lang.fortran.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.halstead.HalsteadSymbol;
import com.puresol.coding.metrics.halstead.LanguageDependedHalsteadMetric;
import com.puresol.uhura.ast.ParserTree;

/**
 * This is the actual implementation of the McCabe metric for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HalsteadMetricImpl implements LanguageDependedHalsteadMetric {

	private static final List<String> operators = new ArrayList<String>();
	static {
		operators.add("SEMICOLON");
		operators.add("EQUALS");
		operators.add("EXCLAMATION_POINT");
		operators.add("PLUS");
		operators.add("QUOTATION_MARK_OR_QUOTE");
		operators.add("MINUS");
		operators.add("PERCENT");
		operators.add("ASTERIK");
		operators.add("AMPERSAND");
		operators.add("SLASH");
		operators.add("TILDE");
		operators.add("BACKSLASH");
		operators.add("LESS_THAN");
		operators.add("LEFT_PARENTHESIS");
		operators.add("GREATER_THAN");
		operators.add("RIGHT_PARENTHESIS");
		operators.add("QUESTION_MARK");
		operators.add("LEFT_SQUARE_BRACKET");
		operators.add("APOSTROPHE");
		operators.add("RIGHT_SQUARE_BRACKET");
		operators.add("GRAVE_ACCENT");
		operators.add("LEFT_CURLY_BRACKET");
		operators.add("CIRCUMFLEX_ACCENT");
		operators.add("RIGHT_CURLY_BRACKET");
		operators.add("VERTICAL_LINE");
		operators.add("COMMA");
		operators.add("CURRENCY_SYMBOL");
		operators.add("DECIMALPOINT_OR_PERIOD");
		operators.add("NUMBER_SIGN");
		operators.add("COLON");
		operators.add("COMMERCIAL_AT");

		operators.add("AND");
		operators.add("EQ");
		operators.add("EQV");
		operators.add("GE");
		operators.add("GT");
		operators.add("LE");
		operators.add("LT");
		operators.add("NE");
		operators.add("NEQV");
		operators.add("NOT");
		operators.add("OR");
	}

	private static final List<String> operatorLiterals = new ArrayList<String>();
	static {
		operatorLiterals.add("ABSTRACT");
		operatorLiterals.add("ALL");
		operatorLiterals.add("ALLOCATABLE");
		operatorLiterals.add("ALLOCATE");
		operatorLiterals.add("ACQUIRED");
		operatorLiterals.add("ASSIGNMENT");
		operatorLiterals.add("ASSOCIATE");
		operatorLiterals.add("ASYNCHRONOUS");
		operatorLiterals.add("BACKSPACE");
		operatorLiterals.add("BIND");
		operatorLiterals.add("BLOCK");
		operatorLiterals.add("BLOCKDATA");
		operatorLiterals.add("C");
		operatorLiterals.add("CALL");
		operatorLiterals.add("CASE");
		operatorLiterals.add("CHARACTER");
		operatorLiterals.add("CLASS");
		operatorLiterals.add("CLOSE");
		operatorLiterals.add("CODIMENSION");
		operatorLiterals.add("COMMON");
		operatorLiterals.add("COMPLEX");
		operatorLiterals.add("CONCURRENT");
		operatorLiterals.add("CONTAINS");
		operatorLiterals.add("CONTIGUOUS");
		operatorLiterals.add("CONTINUE");
		operatorLiterals.add("CRITICAL");
		operatorLiterals.add("CYCLE");
		operatorLiterals.add("DATA");
		operatorLiterals.add("DEALLOCATE");
		operatorLiterals.add("DEFAULT");
		operatorLiterals.add("DEFERRED");
		operatorLiterals.add("DIMENSION");
		operatorLiterals.add("DO");
		operatorLiterals.add("DOUBLE");
		operatorLiterals.add("DOUBLECOMPLEX");
		operatorLiterals.add("DOUBLEPRECISION");
		operatorLiterals.add("ELEMENTAL");
		operatorLiterals.add("ELSE");
		operatorLiterals.add("ELSEIF");
		operatorLiterals.add("ELSEWHERE");
		operatorLiterals.add("END");
		operatorLiterals.add("ENDASSOCIATE");
		operatorLiterals.add("ENDBLOCK");
		operatorLiterals.add("ENDBLOCKDATA");
		operatorLiterals.add("ENDCRITICAL");
		operatorLiterals.add("ENDDO");
		operatorLiterals.add("ENDENUM");
		operatorLiterals.add("ENDFILE");
		operatorLiterals.add("ENDFORALL");
		operatorLiterals.add("ENDFUNCTION");
		operatorLiterals.add("ENDIF");
		operatorLiterals.add("ENDINTERFACE");
		operatorLiterals.add("ENDMODULE");
		operatorLiterals.add("ENDPROCEDURE");
		operatorLiterals.add("ENDPROGRAM");
		operatorLiterals.add("ENDSELECT");
		operatorLiterals.add("ENDSUBMODULE");
		operatorLiterals.add("ENDSUBROUTINE");
		operatorLiterals.add("ENDTYPE");
		operatorLiterals.add("ENDWHERE");
		operatorLiterals.add("ENTRY");
		operatorLiterals.add("ENUM");
		operatorLiterals.add("ENUMERATOR");
		operatorLiterals.add("EQUIVALENCE");
		operatorLiterals.add("ERROR");
		operatorLiterals.add("EXIT");
		operatorLiterals.add("EXTENDS");
		operatorLiterals.add("EXTERNAL");
		operatorLiterals.add("FINAL");
		operatorLiterals.add("FORMAT");
		operatorLiterals.add("FLUSH");
		operatorLiterals.add("FORALL");
		operatorLiterals.add("GENERIC");
		operatorLiterals.add("GO");
		operatorLiterals.add("GOTO");
		operatorLiterals.add("IF");
		operatorLiterals.add("IMPLICIT");
		operatorLiterals.add("IMPORT");
		operatorLiterals.add("IMPURE");
		operatorLiterals.add("IN");
		operatorLiterals.add("INOUT");
		operatorLiterals.add("INQUIRE");
		operatorLiterals.add("INTEGER");
		operatorLiterals.add("INTENT");
		operatorLiterals.add("INTRINSIC");
		operatorLiterals.add("IS");
		operatorLiterals.add("KIND");
		operatorLiterals.add("LEN");
		operatorLiterals.add("LOCK");
		operatorLiterals.add("LOGICAL");
		operatorLiterals.add("MEMORY");
		operatorLiterals.add("MODULE");
		operatorLiterals.add("MOLD");
		operatorLiterals.add("NAME");
		operatorLiterals.add("NAMELIST");
		operatorLiterals.add("NON");
		operatorLiterals.add("NONE");
		operatorLiterals.add("NOPASS");
		operatorLiterals.add("NULLIFY");
		operatorLiterals.add("ONLY");
		operatorLiterals.add("OPEN");
		operatorLiterals.add("OPERATOR");
		operatorLiterals.add("OPTIONAL");
		operatorLiterals.add("OUT");
		operatorLiterals.add("OVERRIDABLE");
		operatorLiterals.add("PARAMETER");
		operatorLiterals.add("PASS");
		operatorLiterals.add("POINTER");
		operatorLiterals.add("PRECISION");
		operatorLiterals.add("PRINT");
		operatorLiterals.add("PRIVATE");
		operatorLiterals.add("PROCEDURE");
		operatorLiterals.add("PROGRAM");
		operatorLiterals.add("PROTECTED");
		operatorLiterals.add("PUBLIC");
		operatorLiterals.add("PURE");
		operatorLiterals.add("READ");
		operatorLiterals.add("REAL");
		operatorLiterals.add("RECURSIVE");
		operatorLiterals.add("RESULT");
		operatorLiterals.add("RETURN");
		operatorLiterals.add("REWIND");
		operatorLiterals.add("SAVE");
		operatorLiterals.add("SELECT");
		operatorLiterals.add("SELECTCASE");
		operatorLiterals.add("SELECTTYPE");
		operatorLiterals.add("SEQUENCE");
		operatorLiterals.add("SOURCE");
		operatorLiterals.add("STOP");
		operatorLiterals.add("SUBMODULE");
		operatorLiterals.add("SUBROUTINE");
		operatorLiterals.add("SYNC");
		operatorLiterals.add("TARGET");
		operatorLiterals.add("THEN");
		operatorLiterals.add("TO");
		operatorLiterals.add("TYPE");
		operatorLiterals.add("UNLOCK");
		operatorLiterals.add("USE");
		operatorLiterals.add("VALUE");
		operatorLiterals.add("VOLATILE");
		operatorLiterals.add("WAIT");
		operatorLiterals.add("WHERE");
		operatorLiterals.add("WHILE");
		operatorLiterals.add("WRITE");

	}

	@Override
	public HalsteadSymbol getHalsteadResult(ParserTree node) {
		if (operators.contains(node.getName())) {
			return new HalsteadSymbol(true, true, node.getText());
		}
		if (operatorLiterals.contains(node.getText())) {
			return new HalsteadSymbol(true, true, node.getText());
		}
		return new HalsteadSymbol(false, false, node.getText());
	}
}
