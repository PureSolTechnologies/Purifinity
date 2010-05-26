package com.puresol.coding;

import java.io.File;
import java.util.List;

import com.puresol.coding.analysis.Analyser;
import com.puresol.parser.TokenDefinition;
import com.puresol.utils.ClassInstantiationException;

/**
 * This interface is the central interface for a programming language
 * specification.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgrammingLanguage {

    /**
     * This method returns the name of the programming language.
     * 
     * @return
     */
    public String getName();

    /**
     * This method specifies whether the programming language is object oriented
     * or not.
     * 
     * @return
     */
    public boolean isObjectOriented();

    /**
     * This method specifies whether the programming language is suitable for a
     * specified file or not.
     * 
     * @return
     */
    public boolean isSuitable(File file);

    /**
     * This method is a factory method for analyser objects for the programming
     * language for a specified file within a specified project directory.
     * 
     * @param directory
     * @param file
     * @return
     * @throws ClassInstantiationException
     */
    public Analyser createAnalyser(File directory, File file)
	    throws ClassInstantiationException;

    /**
     * This method returns the programming language's keywords as a list of
     * token definitions.
     * 
     * @return
     */
    public List<Class<? extends TokenDefinition>> getKeywords();

    /**
     * This method returns the programming language's literals as a list of
     * token definitions. The Identifier token definition is appended at the end
     * of this literals, even if it's not really a literal, but for lexical
     * scanning, it's the best place to be.
     * 
     * @return
     */
    public List<Class<? extends TokenDefinition>> getLiterals();

    /**
     * This method returns the programming language's symbols as a list of token
     * definitions.
     * 
     * @return
     */
    public List<Class<? extends TokenDefinition>> getSymbols();
}
