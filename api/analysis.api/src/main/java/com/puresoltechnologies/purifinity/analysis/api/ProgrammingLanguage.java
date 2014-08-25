package com.puresoltechnologies.purifinity.analysis.api;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadSymbol;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;

/**
 * This interface is the central interface for a programming language
 * specification.
 * 
 * Attention! Programming languages are singletons and therefore, it can not be
 * serialized!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgrammingLanguage { // do not Serialize!

	/**
	 * This method returns the name of the programming language.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns the version of the programming language.
	 * 
	 * @return
	 */
	public String getVersion();

	/**
	 * This method specifies whether the programming language is suitable for a
	 * specified file or not.
	 * 
	 * @return
	 */
	public boolean isSuitable(SourceCodeLocation source);

	/**
	 * This method returns the grammar of the programming language.
	 * 
	 * @return
	 */
	public LanguageGrammar getGrammar();

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of token in context to the language.
	 * 
	 * @param token
	 *            is the token to be checked.
	 * @return The {@link SLOCType} is returned. If the type is not COMMENT,
	 *         BLANK or PRODUCTIVE (e.g. is a line terminator), PHYSICAL is
	 *         returned. null must never be returned!!!!
	 */
	public SLOCType getType(AbstractTerminal token);

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of node in context to the language.
	 * 
	 * @param token
	 *            is the token to be checked.
	 * @return If true is returned the current node is a part of the language
	 *         specification which build an own structural block.
	 */
	public boolean cascadingNode(UniversalSyntaxTree node);

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of node in context to the language.
	 * 
	 * @param node
	 *            is the node to be checked.
	 * @return True is returned in cases the node increases the cyclomatic
	 *         complexity within source codes.
	 */
	public int increasesCyclomaticComplexityBy(AbstractProduction production);

	/**
	 * This method is implemented in language packs for distinguishing the type
	 * of token in context to the language.
	 * 
	 * @param node
	 *            is the node to be checked. The node is needed to check context
	 *            information like for parenthesis in context of loop or if
	 *            constructs.
	 * @return The HalsteadResult objects contains information about the current
	 *         node and it's countability.
	 */
	public HalsteadSymbol getHalsteadResult(AbstractTerminal node);

}
