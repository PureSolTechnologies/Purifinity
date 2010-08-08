package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.AmpersandAssign;
import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.coding.lang.java.source.symbols.BitOrAssign;
import com.puresol.coding.lang.java.source.symbols.CaretAssign;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanAssign;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanGreaterThanAssign;
import com.puresol.coding.lang.java.source.symbols.LessThanLessThanAssign;
import com.puresol.coding.lang.java.source.symbols.MinusAssign;
import com.puresol.coding.lang.java.source.symbols.PlusAssign;
import com.puresol.coding.lang.java.source.symbols.PercentAssign;
import com.puresol.coding.lang.java.source.symbols.SlashAssign;
import com.puresol.coding.lang.java.source.symbols.StarAssign;
import com.puresol.parser.tokens.TokenDefinition;

/**
 * <pre>
 * assignmentOperator 
 *     :   '='
 *     |   '+='
 *     |   '-='
 *     |   '*='
 *     |   '/='
 *     |   '&='
 *     |   '|='
 *     |   '^='
 *     |   '%='
 *     |    '<' '<' '='
 *     |    '>' '>' '>' '='
 *     |    '>' '>' '='
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AssignmentOperators {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();
    static {
	DEFINITIONS.add(Assign.class);
	DEFINITIONS.add(PlusAssign.class);
	DEFINITIONS.add(MinusAssign.class);
	DEFINITIONS.add(StarAssign.class);
	DEFINITIONS.add(SlashAssign.class);
	DEFINITIONS.add(AmpersandAssign.class);
	DEFINITIONS.add(BitOrAssign.class);
	DEFINITIONS.add(CaretAssign.class);
	DEFINITIONS.add(PercentAssign.class);
	DEFINITIONS.add(LessThanLessThanAssign.class);
	DEFINITIONS.add(GreaterThanGreaterThanGreaterThanAssign.class);
	DEFINITIONS.add(GreaterThanGreaterThanAssign.class);
    }

}
